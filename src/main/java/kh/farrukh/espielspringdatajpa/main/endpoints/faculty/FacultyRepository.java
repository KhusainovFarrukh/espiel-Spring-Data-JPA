package kh.farrukh.espielspringdatajpa.main.endpoints.faculty;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository - base marker class, used to make finding repository beans in classpath
 * easier for spring framework
 * CrudRepository - for generic CRUD operations
 * PagingAndSortingRepository - provides additional methods to retrieve entities using
 * the pagination and sorting abstraction
 * JpaRepository - JPA specific extension (with flush and batch)
 * <p>
 * read about flush and batch:
 * https://www.baeldung.com/spring-data-jpa-save-saveandflush
 * https://www.baeldung.com/spring-data-jpa-batch-inserts
 * <p>
 * read about QBE (query by example) (it is very good thing):
 * https://www.techopedia.com/definition/5459/query-by-example-qbe
 * https://www.baeldung.com/spring-data-query-by-example
 */
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
