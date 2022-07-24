package kh.farrukh.espielspringdatajpa.main.endpoints.faculty;

import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kh.farrukh.espielspringdatajpa.main.utils.constants.Endpoints.ENDPOINT_FACULTY;

@RestController
@RequestMapping(ENDPOINT_FACULTY)
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public ResponseEntity<PagingResponse<Faculty>> getFaculties(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "order_by", defaultValue = "asc") String orderBy
    ) {
        return new ResponseEntity<>(
                facultyService.getFaculties(page, pageSize, sortBy, orderBy), HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        return new ResponseEntity<>(facultyService.getFacultyById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@Valid @RequestBody FacultyDTO facultyDto) {
        return new ResponseEntity<>(facultyService.addFaculty(facultyDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> updateFaculty(
            @PathVariable long id,
            @Valid @RequestBody FacultyDTO facultyDto
    ) {
        return new ResponseEntity<>(facultyService.updateFaculty(id, facultyDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFacultyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
