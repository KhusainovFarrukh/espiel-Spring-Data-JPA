package kh.farrukh.espielspringdatajpa.endpoints.staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Page<Staff> findAllByDepartment_Id(long departmentId, Pageable pageable);

    Page<Staff> findAllByDepartment_Faculty_Id(long facultyId, Pageable pageable);

    Page<Staff> findAllByDepartment_IdAndDepartment_Faculty_Id(long departmentId, long facultyId, Pageable pageable);
}
