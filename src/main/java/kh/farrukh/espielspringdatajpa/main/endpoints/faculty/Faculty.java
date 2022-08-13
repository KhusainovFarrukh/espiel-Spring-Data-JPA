package kh.farrukh.espielspringdatajpa.main.endpoints.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kh.farrukh.espielspringdatajpa.main.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

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
 * unique key with random name.
 * Unique constraints can be composite:
 * https://stackoverflow.com/questions/8245935/creating-a-composite-unique-constraints-on-multiple-columns
 *
 * catalog - Catalogs are named collections of schemas in an SQL-environment. An SQL-environment contains zero
 * or more catalogs. A catalog contains one or more schemas, but always contains a schema named INFORMATION_SCHEMA
 * that contains the views and domains of the Information Schema
 *
 * index - A SQL index is used to retrieve data from a database very fast. Indexing a table or view is, without a doubt,
 * one of the best ways to improve the performance of queries and applications. A SQL index is a quick lookup table for
 * finding records users need to search frequently. It is very useful for connecting the relational tables and
 * searching large tables.
 * more about index: https://www.sqlshack.com/sql-index-overview-and-strategy/
 */
// TODO: 8/13/22 secondary table
@Table(name = "faculties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = EntityWithId.ID_GENERATOR, sequenceName = "faculty_sequence", allocationSize = 1)
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
     * columnDefinition - The SQL fragment that is used when generating the DDL for the column.
     * For example, to create a default value (or describing sql type of column, and for other works)
     * directly in the SQL table definition, we can use the columnDefinition parameter.
     * https://www.baeldung.com/jpa-default-column-values
     * https://stackoverflow.com/a/17482163/18366962
     * <p>
     * length - (Optional) The column length. (Applies only if a string-valued column is used.)
     * <p>
     * precision - (Optional) The precision for a decimal (exact numeric) column.
     * (Applies only if a decimal column is used.)
     * <p>
     * scale - (Optional) The scale for a decimal (exact numeric) column.
     * (Applies only if a decimal column is used.)
     * <p>
     * https://stackoverflow.com/a/4084093/18366962
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;
    @JsonIgnoreProperties("department")
    @OneToOne
    private Staff dean;

    public Faculty(FacultyDTO facultyDTO, StaffRepository staffRepository) {
        BeanUtils.copyProperties(facultyDTO, this);
        if (facultyDTO.getDeanId() != null) {
            this.dean = staffRepository.findById(facultyDTO.getDeanId()).orElseThrow(
                    () -> new ResourceNotFoundException("Staff", "id", facultyDTO.getDeanId())
            );
        }
    }
}
