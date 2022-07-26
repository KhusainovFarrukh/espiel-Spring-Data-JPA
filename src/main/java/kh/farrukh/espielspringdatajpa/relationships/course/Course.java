package kh.farrukh.espielspringdatajpa.relationships.course;

import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_id_sequence")
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    // TODO: 7/26/22 more about FetchType
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books;
}
