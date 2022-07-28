package kh.farrukh.espielspringdatajpa.relationships.student_card;

import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_cards")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_card_id_generator")
    @SequenceGenerator(name = "student_card_id_generator", sequenceName = "student_card_id_sequence")
    private long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    /**
     * JoinColumn is not required here. It is only for customization
     *
     * Specifies a column for joining an entity association or element
     * collection. If the JoinColumn annotation itself is
     * defaulted, a single join column is assumed and the default values
     * apply.
     */
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student owner;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public StudentCard(long id, Student owner) {
        this.id = id;
        this.owner = owner;
    }
}
