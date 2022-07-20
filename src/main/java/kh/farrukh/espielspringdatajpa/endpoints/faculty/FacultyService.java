package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import kh.farrukh.espielspringdatajpa.utils.paging_sorting.PagingResponse;

// TODO: 7/20/22 read about if service interface is really needed or not
public interface FacultyService {

    PagingResponse<Faculty> getFaculties(int pageNumber, int pageSize, String sortBy, String orderBy);

    Faculty getFacultyById(long id);

    Faculty addFaculty(FacultyDTO facultyDTO);

    Faculty updateFaculty(long id, FacultyDTO facultyDTO);

    void deleteFacultyById(long id);
}
