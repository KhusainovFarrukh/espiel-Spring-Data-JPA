package kh.farrukh.espielspringdatajpa.fetch_type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fetch_child_entity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;
}
