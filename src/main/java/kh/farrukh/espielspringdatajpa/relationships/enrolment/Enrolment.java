package kh.farrukh.espielspringdatajpa.relationships.enrolment;

import kh.farrukh.espielspringdatajpa.relationships.course.Course;
import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "enrolments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Enrolment {

    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @ToString.Exclude
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @ToString.Exclude
    @MapsId("courseId")
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private EnrolmentType type;

    public Enrolment(Student student, Course course, EnrolmentType type) {
        this.id = new EnrolmentId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
        this.type = type;
    }
}
