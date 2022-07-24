package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kh.farrukh.espielspringdatajpa.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.exception.custom_exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import static kh.farrukh.espielspringdatajpa.base.entity.EntityWithId.ID_GENERATOR;

/**
 * There is no @Entity annotation in JDBC
 */
/**
 * The annotation to configure the mapping from a class to a database table.
 *
 * todo more about schema()
 * todo name vs value
 */
@Table(name = "faculties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// TODO: 7/22/22 id generation with sequence in JDBC
@SequenceGenerator(name = ID_GENERATOR, sequenceName = "faculty_sequence", allocationSize = 1)
public class Faculty extends EntityWithId {

    /**
     * The annotation to configure the mapping from an attribute to a database column.
     *
     * todo replacement for nullable/updatable/insertable/columnDefinition in JDBC
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;
    @JsonIgnoreProperties("department")
    // TODO: 7/22/22 implement N-to-M relationships in JDBC
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
