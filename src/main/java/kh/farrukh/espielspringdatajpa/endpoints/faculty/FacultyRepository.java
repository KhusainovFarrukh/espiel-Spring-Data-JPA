package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long> {

}
