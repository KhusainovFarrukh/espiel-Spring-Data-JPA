package kh.farrukh.espielspringdatajpa.endpoints.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Page<Department> findAllByFaculty_Id(long facultyId, Pageable pageable);
}
