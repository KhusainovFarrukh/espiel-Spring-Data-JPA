package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import org.springframework.data.jpa.repository.JpaRepository;

// TODO: 7/20/22 read about repository types and jpa repo
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
