package kh.farrukh.espielspringdatajpa.relationships.course;

import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "courses",
        uniqueConstraints = {@UniqueConstraint(name = "course_title_unique_key", columnNames = "title")}
)
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_id_sequence")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * like ManyToMany but non-owning side data is single (One)
     * <p>
     * optional - Whether the association is optional. If set to false then a non-null relationship must always exist.
     * default value is true
     */
    @ManyToOne(optional = false)
    private Teacher teacher;

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
     * <p>
     * mappedBy - The field that owns the relationship. Required unless the relationship is unidirectional.
     * <p>
     * todo more about cascade
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Book> books;
    /**
     * todo fix FetchType.LAZY: no Session exception
     *
     * Possible solutions:
     * 1. Transactional annotation (https://stackoverflow.com/a/32276916/18366962)
     * NOT WORKED
     *
     *
     * 2. https://stackoverflow.com/a/50882857/18366962
     * 3. https://stackoverflow.com/a/21575368/18366962
     * 4. https://stackoverflow.com/a/39465150/18366962
     * 5. https://stackoverflow.com/a/39372379/18366962
     * 6. https://stackoverflow.com/a/57030407/18366962
     * 7. https://stackoverflow.com/a/69611021/18366962
     * 8. Idea: custom method in repo
     * 9. search for other possible solutions
     * 10. ask communities for other possible solutions
     *
     *
     * https://www.baeldung.com/hibernate-initialize-proxy-exception
     * https://thorben-janssen.com/lazyinitializationexception/
     * https://javarevisited.blogspot.com/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html
     * https://qna.habr.com/q/564704
     * https://blog.frankel.ch/guide-lazyinitializationexception/
     */
    @Override
    @Transactional
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", books=" + getBooks() +
                '}';
    }
}
