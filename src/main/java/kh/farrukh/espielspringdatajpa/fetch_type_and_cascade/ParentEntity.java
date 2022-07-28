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

    // TODO: 7/28/22 about orphanRemoval
    @OneToMany(
            mappedBy = "parent",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE,
                    CascadeType.MERGE
            }
    )
    private Set<ChildEntity> children;
}
