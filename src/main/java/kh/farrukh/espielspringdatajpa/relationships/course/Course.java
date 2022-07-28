package kh.farrukh.espielspringdatajpa.relationships.course;

import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "courses",
        uniqueConstraints = {@UniqueConstraint(name = "course_title_unique_key", columnNames = "title")}
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Book> books;

    /**
     * FetchType.LAZY: no Session exception
     * !!!
     * There are only 2 good solutions to this problem:
     * <p>
     * 1. You initialize all required associations when you load the entity using a LEFT JOIN FETCH clause
     * or a @NamedEntityGraph or the EntityGraph API.
     * 2. You use a DTO projection instead of entities. DTOs don’t support lazy loading, and you need
     * to fetch all required information within your service layer.
     * <p>
     * Possible solutions:
     * 1. Transactional annotation (https://stackoverflow.com/a/32276916/18366962)
     * NOT WORKED
     * <p>
     * 2. enable_lazy_load_no_trans=true (https://stackoverflow.com/a/50882857/18366962)
     * WORKED
     * todo discuss about it: https://stackoverflow.com/a/39723223/18366962
     * <p>
     * 3. current_session_context_class (https://stackoverflow.com/a/21575368/18366962)
     * NOT APPLICABLE
     * <p>
     * 4. JOIN FETCH (https://stackoverflow.com/a/39465150/18366962)
     * PROBABLY WORKS, BUT VERY MANUAL (needs writing sql query)
     * <p>
     * 5. @Proxy(lazy=false) (https://stackoverflow.com/a/39372379/18366962)
     * NOT WORKED
     * <p>
     * 6. DTO projection (https://stackoverflow.com/a/57030407/18366962)
     * MAYBE GOOD SOLUTION FOR SOME CASES, BUT NOT COMPATIBLE WITH CURRENT DAO (REPO) LAYER IMPLEMENTATION
     * AND NEEDS MORE EXTRA CODE
     * <p>
     * 7. EntityGraphApi (https://stackoverflow.com/a/69611021/18366962)
     * GOOD FOR PERFORMANCE, BUT NEEDS MANUAL SQL QUERY AND MORE EXTRA CODE
     * <p>
     * 8. Idea: custom method in repo: looks like solution-6 (DTO projection)
     * <p>
     * 9. search for other possible solutions: currently it is all, imho
     * <p>
     * 10. ask communities for other possible solutions: most advised is solution-2 (enable_lazy_load_no_trans=true)
     * <p>
     * ADDITIONAL INFO:
     * https://www.baeldung.com/hibernate-initialize-proxy-exception
     * https://thorben-janssen.com/lazyinitializationexception/
     * https://javarevisited.blogspot.com/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html
     * https://qna.habr.com/q/564704
     * https://blog.frankel.ch/guide-lazyinitializationexception/
     */
}
