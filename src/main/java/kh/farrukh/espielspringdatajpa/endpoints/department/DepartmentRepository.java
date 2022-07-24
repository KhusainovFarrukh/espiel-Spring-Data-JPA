package kh.farrukh.espielspringdatajpa.endpoints.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    Page<Department> findAllByFaculty_Id(long facultyId, Pageable pageable);
}
