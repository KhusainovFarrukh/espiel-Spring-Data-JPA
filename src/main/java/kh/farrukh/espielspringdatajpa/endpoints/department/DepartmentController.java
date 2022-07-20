package kh.farrukh.espielspringdatajpa.endpoints.department;

import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kh.farrukh.espielspringdatajpa.utils.constants.Endpoints.ENDPOINT_DEPARTMENT;

@RestController
@RequestMapping(ENDPOINT_DEPARTMENT)
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<PagingResponse<Department>> getDepartments(
            @RequestParam(name = "faculty_id", required = false) Long facultyId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "order_by", defaultValue = "asc") String orderBy
    ) {
        return new ResponseEntity<>(
                departmentService.getDepartments(facultyId, page, pageSize, sortBy, orderBy), HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody DepartmentDTO departmentDto) {
        return new ResponseEntity<>(departmentService.addDepartment(departmentDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable long id,
            @Valid @RequestBody DepartmentDTO departmentDto
    ) {
        return new ResponseEntity<>(departmentService.updateDepartment(id, departmentDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
