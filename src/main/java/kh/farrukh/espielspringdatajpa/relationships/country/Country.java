package kh.farrukh.espielspringdatajpa.relationships.country;

import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_sequence")
    private long id;

    private String name;

    @OneToMany(mappedBy = "phoneNumber.country")
    private Set<Student> students;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
