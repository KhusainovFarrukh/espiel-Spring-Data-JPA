package kh.farrukh.espielspringdatajpa.endpoints.faculty;


import kh.farrukh.espielspringdatajpa.exception.custom_exceptions.ResourceNotFoundException;
import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;
import kh.farrukh.espielspringdatajpa.utils.paging_sorting.SortUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static kh.farrukh.espielspringdatajpa.utils.checkers.Checkers.checkFacultyId;
import static kh.farrukh.espielspringdatajpa.utils.checkers.Checkers.checkPageNumber;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public PagingResponse<Faculty> getFaculties(
            int page,
            int pageSize,
            String sortBy,
            String orderBy
    ) {
        checkPageNumber(page);
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
        return facultyRepository.save(new Faculty(facultyDto));
    }

    // TODO: 7/20/22 read about update logic best practices
    @Override
    public Faculty updateFaculty(long id, FacultyDTO facultyDto) {
        Faculty existingFaculty = facultyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", id)
        );

        existingFaculty.setName(facultyDto.getName());

        return facultyRepository.save(existingFaculty);
    }

    @Override
    public void deleteFacultyById(long id) {
        checkFacultyId(facultyRepository, id);
        facultyRepository.deleteById(id);
    }
}