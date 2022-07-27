package kh.farrukh.espielspringdatajpa.fetch_type_and_cascade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentEntityRepository extends JpaRepository<ParentEntity, Long> {
}
