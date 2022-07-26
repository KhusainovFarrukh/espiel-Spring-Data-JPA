package kh.farrukh.espielspringdatajpa.relationships.teacher;

import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"books", "pupils"})
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

    /**
     * Teacher is non-owning side of bidirectional association.
     * When creating/updating teacher, you can not assign (associate) books to it.
     */
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books;

    /**
     * !!! OneToMany is always non-owning-side of bidirectional association.
     * <p>
     * Teacher is non-owning side of bidirectional association.
     * When creating/updating teacher, you can not assign (associate) students (pupils) to it.
     */
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private Set<Student> pupils;

    public Teacher(long id, String firstName, String lastName, PhoneNumber phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
