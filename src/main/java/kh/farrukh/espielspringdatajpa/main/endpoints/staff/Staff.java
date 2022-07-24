package kh.farrukh.espielspringdatajpa.main.endpoints.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import kh.farrukh.espielspringdatajpa.main.base.entity.EntityWithId;
import kh.farrukh.espielspringdatajpa.main.endpoints.department.Department;
import kh.farrukh.espielspringdatajpa.main.endpoints.department.DepartmentRepository;
import kh.farrukh.espielspringdatajpa.main.exception.custom_exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = EntityWithId.ID_GENERATOR, sequenceName = "staff_sequence", allocationSize = 1)
public class Staff extends EntityWithId {

    @JsonProperty("first_name")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @JsonProperty("last_name")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffDegree degree;
    @JsonProperty("main_subject")
    @Column(columnDefinition = "TEXT")
    private String mainSubject;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role;
    @ManyToOne
    private Department department;

    public Staff(StaffDTO staffDTO, DepartmentRepository departmentRepository) {
        BeanUtils.copyProperties(staffDTO, this);
        this.department = departmentRepository.findById(staffDTO.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", staffDTO.getDepartmentId())
        );
    }
}
