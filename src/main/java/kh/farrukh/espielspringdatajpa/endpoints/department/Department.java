package kh.farrukh.espielspringdatajpa.endpoints.department;

import kh.farrukh.espielspringdatajpa.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.exception.custom_exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

import static kh.farrukh.espielspringdatajpa.base.entity.EntityWithId.ID_GENERATOR;

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

    public Department(DepartmentDTO departmentDTO, FacultyRepository facultyRepository) {
        BeanUtils.copyProperties(departmentDTO, this);
        this.faculty = facultyRepository.findById(departmentDTO.getFacultyId()).orElseThrow(
                () -> new ResourceNotFoundException("Faculty", "id", departmentDTO.getFacultyId())
        );
    }
}