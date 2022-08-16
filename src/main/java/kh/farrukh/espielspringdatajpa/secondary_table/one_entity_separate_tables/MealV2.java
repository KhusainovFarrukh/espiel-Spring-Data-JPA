package kh.farrukh.espielspringdatajpa.secondary_table.one_entity_separate_tables;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SecondaryTable(name = "mealv2_allergens", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class MealV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    Double price;

    @Column(table = "mealv2_allergens")
    boolean peanuts;

    @Column(table = "mealv2_allergens")
    boolean celery;

    @Column(table = "mealv2_allergens")
    boolean sesameSeeds;

    public MealV2(String name, String description, Double price, boolean peanuts, boolean celery, boolean sesameSeeds) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.peanuts = peanuts;
        this.celery = celery;
        this.sesameSeeds = sesameSeeds;
    }
}