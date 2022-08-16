package kh.farrukh.espielspringdatajpa.secondary_table.separate_entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MealV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    Double price;

    @OneToOne(mappedBy = "meal", optional = false)
    AllergensV1 allergens;

    public MealV1(String name, String description, Double price, AllergensV1 allergens) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
    }
}