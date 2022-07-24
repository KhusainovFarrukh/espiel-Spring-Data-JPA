package kh.farrukh.espielspringdatajpa.main.endpoints.staff;

import kh.farrukh.espielspringdatajpa.main.endpoints.department.Department;
import kh.farrukh.espielspringdatajpa.main.endpoints.department.DepartmentRepository;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.PagingResponse;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static kh.farrukh.espielspringdatajpa.main.utils.checkers.Checkers.checkPageNumber;
import static kh.farrukh.espielspringdatajpa.main.utils.checkers.Checkers.checkStaffId;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public PagingResponse<Staff> getStaffs(
            Long departmentId,
            Long facultyId,
            int page,
            int pageSize,
            String sortBy,
            String orderBy
    ) {
        checkPageNumber(page);
        Page<Staff> staffs;

        if (departmentId == null && facultyId == null) {
            staffs = staffRepository.findAll(
                    PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            );
        } else if (departmentId == null) {
            staffs = staffRepository.findAllByDepartment_Faculty_Id(
                    facultyId, PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            );
        } else if (facultyId == null) {
            staffs = staffRepository.findAllByDepartment_Id(
                    departmentId, PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            );
        } else {
            staffs = staffRepository.findAllByDepartment_IdAndDepartment_Faculty_Id(
                    departmentId, facultyId, PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
            );
        }

        return new PagingResponse<>(staffs);
    }

    @Override
    public Staff getStaffById(long id) {
        return staffRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Staff", "id", id)
        );
    }

    @Override
    public Staff addStaff(StaffDTO staffDto) {
        return staffRepository.save(new Staff(staffDto, departmentRepository));
    }

    @Override
    public Staff updateStaff(long id, StaffDTO staffDto) {
        Staff existingStaff = staffRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Staff", "id", id)
        );

        Department department = departmentRepository.findById(staffDto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", staffDto.getDepartmentId())
        );

        existingStaff.setFirstName(staffDto.getFirstName());
        existingStaff.setLastName(staffDto.getLastName());
        existingStaff.setMainSubject(staffDto.getMainSubject());
        existingStaff.setDegree(staffDto.getDegree());
        existingStaff.setRole(staffDto.getRole());
        existingStaff.setDepartment(department);

        return staffRepository.save(existingStaff);
    }

    @Override
    public void deleteStaffById(long id) {
        checkStaffId(staffRepository, id);
        staffRepository.deleteById(id);
    }
}
