package kh.farrukh.espielspringdatajpa.main.endpoints.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kh.farrukh.espielspringdatajpa.main.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

import static kh.farrukh.espielspringdatajpa.main.base.entity.EntityWithId.ID_GENERATOR;

@Entity
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