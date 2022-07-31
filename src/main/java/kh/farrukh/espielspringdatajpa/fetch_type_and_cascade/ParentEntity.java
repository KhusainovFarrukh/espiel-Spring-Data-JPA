package kh.farrukh.espielspringdatajpa.fetch_type_and_cascade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fetch_parent_entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * NamedEntityGraph - Used to specify the path and boundaries for a find operation or query.
 * name - The name of the entity graph. Defaults to the entity name of the root entity.
 * attributeNodes - A list of attributes of the entity that are included in this graph.
 * includeAllAttributes - Includes all of the attributes of the annotated entity class as attribute nodes in
 * the NamedEntityGraph without the need to explicitly list them. Included attributes can still be fully specified
 * by an attribute node referencing a subgraph.
 * subgraphs - A list of subgraphs that are included in the entity graph. These are referenced by name
 * from NamedAttributeNode definitions
 * subclassSubgraphs - A list of subgraphs that will add additional attributes for subclasses of the annotated entity
 * class to the entity graph. Specified attributes from superclasses are included in subclasses.
 */
@NamedEntityGraph(name = "parent_with_children", attributeNodes = @NamedAttributeNode(value = "children"))
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    /**
     * orphanRemoval - As stated earlier, its usage is to delete orphaned entities from the database.
     * An entity that is no longer attached to its parent is the definition of being an orphan.
     * <p>
     * In our case, an OrderRequest has a collection of LineItem objects where we use the @OneToMany
     * annotation to identify the relationship. This is where we also set the orphanRemoval attribute
     * to true. To detach a LineItem from an OrderRequest, we can use the removeLineItem method that
     * we previously created.
     * <p>
     * With everything in place, once we use the removeLineItem method and save the OrderRequest,
     * the deletion of the orphaned LineItem from the database should happen.
     * <p>
     * READ MORE: https://www.baeldung.com/jpa-cascade-remove-vs-orphanremoval
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChildEntity> children;
}
