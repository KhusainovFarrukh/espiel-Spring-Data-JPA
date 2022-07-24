package kh.farrukh.espielspringdatajpa.main.endpoints.staff;

import kh.farrukh.espielspringdatajpa.main.utils.paging_sorting.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kh.farrukh.espielspringdatajpa.main.utils.constants.Endpoints.ENDPOINT_STAFF;

@RestController
@RequestMapping(ENDPOINT_STAFF)
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<PagingResponse<Staff>> getStaffs(
            @RequestParam(name = "department_id", required = false) Long departmentId,
            @RequestParam(name = "faculty_id", required = false) Long facultyId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "order_by", defaultValue = "asc") String orderBy
    ) {
        return new ResponseEntity<>(
                staffService.getStaffs(departmentId, facultyId, page, pageSize, sortBy, orderBy),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable long id) {
        return new ResponseEntity<>(staffService.getStaffById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Staff> addStaff(@Valid @RequestBody StaffDTO staffDto) {
        return new ResponseEntity<>(staffService.addStaff(staffDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable long id,
            @Valid @RequestBody StaffDTO staffDto
    ) {
        return new ResponseEntity<>(staffService.updateStaff(id, staffDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable long id) {
        staffService.deleteStaffById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
