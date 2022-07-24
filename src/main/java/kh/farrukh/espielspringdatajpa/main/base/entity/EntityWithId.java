package kh.farrukh.espielspringdatajpa.main.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is a base class for all entities that have an id.
 */
@Getter
@Setter
/**
 * Designates a class whose mapping information is applied to the entities that inherit from it.
 * A mapped superclass has no separate table defined for it.
 * A class designated with the MappedSuperclass annotation can be mapped in the same way as an
 * entity except that the mappings will apply only to its subclasses since no table exists for
 * the mapped superclass itself.
 */
@MappedSuperclass
public abstract class EntityWithId {

    public static final String ID_GENERATOR = "id_generator";

    /**
     * todo what does it mean
     * The use of the GeneratedValue annotation is only required to be supported for simple primary keys.
     * Use of the GeneratedValue annotation is not supported for derived primary keys.
     * <p/>
     * GenerationTypes (https://www.youtube.com/watch?v=qn9SbW44rQ8)
     * AUTO - selects preferred generation strategy depending on db dialect.
     * Currently, hibernate selects IDENTITY for MySQL, SEQUENCE for PostgreSQL...
     * It is best practice to define GenerationType yourself
     * IDENTITY - uses auto-increment column for generating ids. Best option when
     * DB does not support database sequence type. (for example: MySQL)
     * SEQUENCE - most optimized strategy. Uses sequence database type (not table) to
     * generate ids. Optimized also with batching (JDBC batching) and other features.
     * Best option to use, if DB supports it.
     * Additional: to use SEQUENCE with EntityWithId (MappedSuperclass), set strategy to
     * sequence, and annotate each entity subclass with @SequenceGenerator and create separate sequences.
     * Else there will be one sequence for all entities, it is very bad
     * TABLE - worst option, simulation of sequence for using when DB does not support
     * sequence database type. Slowest one.
     * todo what is batching
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_GENERATOR)
    @Column(updatable = false, nullable = false)
    private long id;
}