package kh.farrukh.espielspringdatajpa.relationships.room;

import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_generator")
    @SequenceGenerator(name = "room_id_generator", sequenceName = "room_id_sequence")
    private long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Teacher owner;
}
