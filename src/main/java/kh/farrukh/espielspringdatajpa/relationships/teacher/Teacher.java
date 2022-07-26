package kh.farrukh.espielspringdatajpa.relationships.teacher;

import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "books")
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
}
