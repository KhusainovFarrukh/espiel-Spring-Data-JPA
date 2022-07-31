package kh.farrukh.espielspringdatajpa.relationships.enrolment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, EnrolmentId> {
}
