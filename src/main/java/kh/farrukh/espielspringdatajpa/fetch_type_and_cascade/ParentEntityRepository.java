package kh.farrukh.espielspringdatajpa.fetch_type_and_cascade;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentEntityRepository extends JpaRepository<ParentEntity, Long> {

    @Query("select p from ParentEntity p")
    /**
     * Annotation to configure the JPA 2.1 EntityGraphs that should be used on repository methods.
     * Since 1.9 we support the definition of dynamic EntityGraphs by allowing to customize the fetch-graph via
     * attributePaths() ad-hoc fetch-graph configuration. If attributePaths() are specified then we ignore the
     * entity-graph name value() and treat this EntityGraph as dynamic.
     *
     * type: default is FETCH (!!!)
     * LOAD - When the javax.persistence.loadgraph property is used to specify an entity graph,
     * attributes that are specified by attribute nodes of the entity graph are treated as FetchType.EAGER and
     * attributes that are not specified are treated according to their specified or default FetchType.
     * FETCH - When the javax.persistence.fetchgraph property is used to specify an entity graph,
     * attributes that are specified by attribute nodes of the entity graph are treated as FetchType.EAGER and
     * attributes that are not specified are treated as FetchType.LAZY
     */
    @EntityGraph(value = "parent_with_children", type = EntityGraph.EntityGraphType.LOAD)
    List<ParentEntity> findAllWithChildren();
}
