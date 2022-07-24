package kh.farrukh.espielspringdatajpa.main.endpoints.department;

import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRole;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.PagingResponse;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static kh.farrukh.espielspringdatajpa.main.utils.checkers.Checkers.*;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final StaffRepository staffRepository;

    @Override
    public PagingResponse<Department> getDepartments(
            Long facultyId,
            int page,
            int pageSize,
            String sortBy,
            String orderBy
    ) {
        checkPageNumber(page);
        if (facultyId == null) {
            return new PagingResponse<>(departmentRepository.findAll(
                    PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            ));
        } else {
            checkFacultyId(facultyRepository, facultyId);
            return new PagingResponse<>(departmentRepository.findAllByFaculty_Id(
                    facultyId, PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            ));
        }
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
    }

    @Override
    public Department addDepartment(DepartmentDTO departmentDto) {
        return departmentRepository.save(new Department(departmentDto, facultyRepository, staffRepository));
    }

    @Override
    public Department updateDepartment(long id, DepartmentDTO departmentDto) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        Faculty faculty = facultyRepository.findById(departmentDto.getFacultyId()).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", departmentDto.getFacultyId())
        );

        if (departmentDto.getHeadId() != null) {
            Staff head = staffRepository.findById(departmentDto.getHeadId()).orElseThrow(
                    () -> new ResourceNotFoundException("Staff", "id", departmentDto.getFacultyId())
            );
            Staff prevHead = existingDepartment.getHead();
            if (prevHead != null) {
                prevHead.setRole(StaffRole.ADMINISTRATIVE_WORKER);
                staffRepository.save(prevHead);
            }
            head.setRole(StaffRole.HEAD_OF_DEPARTMENT);
            staffRepository.save(head);
            existingDepartment.setHead(head);
        }

        existingDepartment.setName(departmentDto.getName());
        existingDepartment.setFaculty(faculty);

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartmentById(long id) {
        checkDepartmentId(departmentRepository, id);
        departmentRepository.deleteById(id);
    }
}
