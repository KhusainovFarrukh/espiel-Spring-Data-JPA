package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;

public interface FacultyService {

    PagingResponse<Faculty> getFaculties(int pageNumber, int pageSize, String sortBy, String orderBy);

    Faculty getFacultyById(long id);

    Faculty addFaculty(FacultyDTO facultyDTO);

    Faculty updateFaculty(long id, FacultyDTO facultyDTO);

    void deleteFacultyById(long id);
}
