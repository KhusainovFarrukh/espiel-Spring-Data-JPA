package kh.farrukh.espielspringdatajpa.relationships.student;

import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
    @SequenceGenerator(name = "student_id_generator", sequenceName = "student_id_sequence")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Embedded
    private PhoneNumber phoneNumber;

    /**
     * !!! ManyToOne is always owning-side of bidirectional association.
     * !!! JoinTable is not required, but you can use it to generate another table for associations
     * <p>
     * Student is owning side of bidirectional association.
     * When creating/updating student, you can assign (associate) teacher to it.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher tutor;
}
