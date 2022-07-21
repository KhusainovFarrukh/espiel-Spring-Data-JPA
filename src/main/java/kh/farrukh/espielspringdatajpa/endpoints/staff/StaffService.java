package kh.farrukh.espielspringdatajpa.endpoints.staff;

import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;

public interface StaffService {

    PagingResponse<Staff> getStaffs(
            Long departmentId,
            Long facultyId,
            int pageNumber,
            int pageSize,
            String sortBy,
            String orderBy
    );

    Staff getStaffById(long id);

    Staff addStaff(StaffDTO DTO);

    Staff updateStaff(long id, StaffDTO DTO);

    void deleteStaffById(long id);
}
