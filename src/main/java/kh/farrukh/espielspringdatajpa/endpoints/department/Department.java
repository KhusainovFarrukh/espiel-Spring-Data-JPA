package kh.farrukh.espielspringdatajpa.endpoints.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kh.farrukh.espielspringdatajpa.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.FacultyRepository;
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

@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = ID_GENERATOR, sequenceName = "department_sequence", allocationSize = 1)
public class Department extends EntityWithId {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;
    @ManyToOne
    private Faculty faculty;
    @JsonIgnoreProperties("department")
    @OneToOne
    private Staff head;

    public Department(DepartmentDTO departmentDTO, FacultyRepository facultyRepository, StaffRepository staffRepository) {
        BeanUtils.copyProperties(departmentDTO, this);
        this.faculty = facultyRepository.findById(departmentDTO.getFacultyId()).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", departmentDTO.getFacultyId())
        );
        if (departmentDTO.getHeadId() != null) {
            this.head = staffRepository.findById(departmentDTO.getHeadId()).orElseThrow(
                    () -> new ResourceNotFoundException("Staff", "id", departmentDTO.getHeadId())
            );
        }
    }
}