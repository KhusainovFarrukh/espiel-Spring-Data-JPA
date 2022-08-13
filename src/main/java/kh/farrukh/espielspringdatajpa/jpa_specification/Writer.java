package kh.farrukh.espielspringdatajpa.jpa_specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    @ManyToMany(mappedBy = "authors")
    private List<Article> articles = Collections.emptyList();

    public Writer(String fullName, Profession profession) {
        this.fullName = fullName;
        this.profession = profession;
    }
}

