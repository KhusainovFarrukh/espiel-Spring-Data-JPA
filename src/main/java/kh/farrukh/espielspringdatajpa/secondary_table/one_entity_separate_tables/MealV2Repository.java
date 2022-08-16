package kh.farrukh.espielspringdatajpa.secondary_table.one_entity_separate_tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealV2Repository extends JpaRepository<MealV2, Long> {
}
