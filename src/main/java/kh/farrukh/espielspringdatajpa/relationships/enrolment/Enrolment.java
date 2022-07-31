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
@IdClass(EnrolmentId.class)
public class Enrolment {

    @Id
    private long studentId;

    @Id
    private long courseId;

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
        this.studentId = student.getId();
        this.courseId = course.getId();
        this.student = student;
        this.course = course;
        this.type = type;
    }
}
