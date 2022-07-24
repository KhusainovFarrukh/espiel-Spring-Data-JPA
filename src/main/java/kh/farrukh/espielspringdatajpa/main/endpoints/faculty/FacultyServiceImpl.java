package kh.farrukh.espielspringdatajpa.main.endpoints.faculty;


import kh.farrukh.espielspringdatajpa.main.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRole;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import kh.farrukh.espielspringdatajpa.main.utils.checkers.Checkers;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.PagingResponse;
import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StaffRepository staffRepository;

    @Override
    public PagingResponse<Faculty> getFaculties(
            int page,
            int pageSize,
            String sortBy,
            String orderBy
    ) {
        Checkers.checkPageNumber(page);
        return new PagingResponse<>(facultyRepository.findAll(
                PageRequest.of(page - 1, pageSize, Sort.by(SortUtils.parseDirection(orderBy), sortBy))
        ));
    }

    @Override
    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", id)
        );
    }

    @Override
    public Faculty addFaculty(FacultyDTO facultyDto) {
        return facultyRepository.save(new Faculty(facultyDto, staffRepository));
    }

    // TODO: 7/20/22 read about update logic best practices
    @Override
    public Faculty updateFaculty(long id, FacultyDTO facultyDto) {
        Faculty existingFaculty = facultyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", id)
        );

        if (facultyDto.getDeanId() != null) {
            Staff dean = staffRepository.findById(facultyDto.getDeanId()).orElseThrow(
                    () -> new ResourceNotFoundException("Staff", "id", facultyDto.getDeanId())
            );
            Staff prevDean = existingFaculty.getDean();
            if (prevDean != null) {
                prevDean.setRole(StaffRole.ADMINISTRATIVE_WORKER);
                staffRepository.save(prevDean);
            }
            dean.setRole(StaffRole.DEAN_OF_FACULTY);
            staffRepository.save(dean);
            existingFaculty.setDean(dean);
        }

        existingFaculty.setName(facultyDto.getName());

        return facultyRepository.save(existingFaculty);
    }

    @Override
    public void deleteFacultyById(long id) {
        Checkers.checkFacultyId(facultyRepository, id);
        facultyRepository.deleteById(id);
    }
}