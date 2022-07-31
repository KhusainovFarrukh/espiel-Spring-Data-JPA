package kh.farrukh.espielspringdatajpa.relationships.student;

import kh.farrukh.espielspringdatajpa.relationships.enrolment.Enrolment;
import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import kh.farrukh.espielspringdatajpa.relationships.student_card.StudentCard;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @AttributeOverrides({
            @AttributeOverride(name = "countryCode", column = @Column(name = "pn_country_code")),
            @AttributeOverride(name = "localNumber", column = @Column(name = "pn_local_number"))
    })
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

    /**
     * !!! There will not be card_id column in students table. (in One-to-One bidirectional association)
     * student_id foreign key (column) will be in student_cards table.
     * But you can get StudentCard from Student and vice-versa.
     */
    @OneToOne(mappedBy = "owner", fetch = FetchType.EAGER)
    @ToString.Exclude
    private StudentCard card;

    @OneToMany(mappedBy = "student")
    private List<Enrolment> enrolments = Collections.emptyList();

    public Student(long id, String firstName, String lastName, PhoneNumber phoneNumber, Teacher tutor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.tutor = tutor;
    }
}
