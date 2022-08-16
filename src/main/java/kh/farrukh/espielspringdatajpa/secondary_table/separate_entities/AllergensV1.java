package kh.farrukh.espielspringdatajpa.secondary_table.separate_entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllergensV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    Long mealId;

    @ToString.Exclude
    @OneToOne
    @PrimaryKeyJoinColumn(name = "meal_id")
    MealV1 meal;

    boolean peanuts;

    boolean celery;

    boolean sesameSeeds;

    public AllergensV1(boolean peanuts, boolean celery, boolean sesameSeeds) {
        this.peanuts = peanuts;
        this.celery = celery;
        this.sesameSeeds = sesameSeeds;
    }
}
