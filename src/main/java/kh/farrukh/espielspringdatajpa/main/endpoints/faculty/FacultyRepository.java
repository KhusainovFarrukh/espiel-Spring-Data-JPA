package kh.farrukh.espielspringdatajpa.main.endpoints.faculty;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository - base marker class, used to make finding repository beans in classpath
 * easier for spring framework
 * CrudRepository - for generic CRUD operations
 * PagingAndSortingRepository - provides additional methods to retrieve entities using
 * the pagination and sorting abstraction
 * JpaRepository - JPA specific extension (with flush and batch)
 * todo more about jpa repo
 * todo what is flush/batch
 * todo what is QBE (query by example)
 */
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
