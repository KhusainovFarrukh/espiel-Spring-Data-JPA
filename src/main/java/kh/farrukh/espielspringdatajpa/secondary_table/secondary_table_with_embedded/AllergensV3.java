package kh.farrukh.espielspringdatajpa.secondary_table.secondary_table_with_embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllergensV3 {

    @Column(table = "mealv3_allergens")
    boolean peanuts;

    @Column(table = "mealv3_allergens")
    boolean celery;

    @Column(table = "mealv3_allergens")
    boolean sesameSeeds;
}