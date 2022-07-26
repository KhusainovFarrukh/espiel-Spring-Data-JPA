package kh.farrukh.espielspringdatajpa.relationships.book;

import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_generator")
    @SequenceGenerator(name = "book_id_generator", sequenceName = "book_id_sequence")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private Integer year;

    /**
     * Book is owning side of bidirectional association.
     * When creating/updating book, you can assign (associate) teachers (authors) with it
     */
    @ManyToMany(fetch = FetchType.EAGER)
    /**
     * JoinTable is not required, it is only for customizing
     * <p>
     * See docs for additional data
     */
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Teacher> authors;
}
