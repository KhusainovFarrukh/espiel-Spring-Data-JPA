package kh.farrukh.espielspringdatajpa.relationships.teacher;

import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_id_generator")
    @SequenceGenerator(name = "teacher_id_generator", sequenceName = "teacher_id_sequence")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Embedded
    private PhoneNumber phoneNumber;
}
