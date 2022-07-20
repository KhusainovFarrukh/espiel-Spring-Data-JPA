package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import kh.farrukh.espielspringdatajpa.base.entity.EntityWithId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static kh.farrukh.espielspringdatajpa.base.entity.EntityWithId.ID_GENERATOR;

/**
 * name - Defaults to the unqualified name of the entity class.
 * This name is used to refer to the entity in queries. The name must not be a
 * reserved literal in the Jakarta Persistence query language.
 */
@Entity(name = "Faculty")
/**
 * Specifies the primary table for the annotated entity.
 * Additional tables may be specified using SecondaryTable or SecondaryTables annotation.
 * <p/>
 * Use uniqueConstraints to generate unique key for some columns with custom name.
 * It is best practice. Because setting unique property of @Column to true generates
 * unique key with random name
 *
 * todo what is catalog of table
 * todo what is index for table
 */
@Table(name = "faculties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = ID_GENERATOR, sequenceName = "faculty_sequence", allocationSize = 1)
public class Faculty extends EntityWithId {

    /**
     * name - Defaults to the property or field name converted to snake_case.
     * <p/>
     * unique - This is a shortcut for the UniqueConstraint annotation at the table level and
     * is useful for when the unique key constraint corresponds to only a single column.
     * This constraint applies in addition to any constraint entailed by primary key mapping
     * and to constraints specified at the table level.
     * But name of constraint will be ugly
     * <p/>
     * insertable/updatable - if set to false, insert/update won't work on db layer (table).
     * But repository.save(entity) will return entity like inserted/updated
     * <p/>
     * todo what can columnDefinition do
     * todo what is precision/scale
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    public Faculty(FacultyDTO FacultyDTO) {
        BeanUtils.copyProperties(FacultyDTO, this);
    }
}
