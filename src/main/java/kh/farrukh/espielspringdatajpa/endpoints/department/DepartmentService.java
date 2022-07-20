package kh.farrukh.espielspringdatajpa.endpoints.department;

import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;

public interface DepartmentService {

    PagingResponse<Department> getDepartments(
            Long facultyId,
            int pageNumber,
            int pageSize,
            String sortBy,
            String orderBy
    );

    Department getDepartmentById(long id);

    Department addDepartment(DepartmentDTO departmentDTO);

    Department updateDepartment(long id, DepartmentDTO departmentDTO);

    void deleteDepartmentById(long id);
}
