package kh.farrukh.espielspringdatajpa.fetch_type_and_cascade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildEntityRepository extends JpaRepository<ChildEntity, Long> {
}
