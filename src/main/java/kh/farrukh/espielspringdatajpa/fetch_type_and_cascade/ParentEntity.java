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
