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

    /**
     * Every many-to-many association has two sides, the owning side
     * and the non-owning, or inverse, side.  The join table is specified
     * on the owning side. If the association is bidirectional, either
     * side may be designated as the owning side.  If the relationship is
     * bidirectional, the non-owning side must use the <code>mappedBy</code> element of
     * the <code>ManyToMany</code> annotation to specify the relationship field or
     * property of the owning side.
     * <p>
     * targetEntity -(Optional) The entity class that is the target of the
     * association. Optional only if the collection-valued
     * relationship property is defined using Java generics.  Must be
     * specified otherwise. Defaults to the parameterized type of
     * the collection when defined using generics.
     *
     * mappedBy - The field that owns the relationship. Required unless the relationship is unidirectional.
     *
     * todo more about cascade
     * todo more about fetch
     */
    // TODO: 7/26/22 more about FetchType
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books;
}
