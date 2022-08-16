package kh.farrukh.espielspringdatajpa.secondary_table.secondary_table_with_embedded;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SecondaryTable(name = "mealv3_allergens", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class MealV3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    Double price;

    @Embedded
    AllergensV3 allergens;

    public MealV3(String name, String description, Double price, AllergensV3 allergens) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
    }
}
