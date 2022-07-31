package kh.farrukh.espielspringdatajpa.relationships.country;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_sequence")
    private long id;

    private String name;
}
