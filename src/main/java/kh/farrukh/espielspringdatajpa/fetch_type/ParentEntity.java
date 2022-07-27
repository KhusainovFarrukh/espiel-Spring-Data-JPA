package kh.farrukh.espielspringdatajpa.fetch_type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

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

    @OneToMany(mappedBy = "parent")
    private Set<ChildEntity> children;
}
