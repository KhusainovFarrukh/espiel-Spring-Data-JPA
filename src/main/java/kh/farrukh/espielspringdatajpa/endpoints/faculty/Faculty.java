package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import kh.farrukh.espielspringdatajpa.base.entity.EntityWithId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: 7/20/22 read about entity and its properties
@Entity
// TODO: 7/20/22 read about table and its properties
@Table(name = "faculties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Faculty extends EntityWithId {

    // TODO: 7/20/22 read about column and its properties
    @Column(nullable = false)
    private String name;

    public Faculty(FacultyDTO FacultyDTO) {
        BeanUtils.copyProperties(FacultyDTO, this);
    }
}
