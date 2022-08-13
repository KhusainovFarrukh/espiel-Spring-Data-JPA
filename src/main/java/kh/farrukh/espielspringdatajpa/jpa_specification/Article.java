package kh.farrukh.espielspringdatajpa.jpa_specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer publishedYear;

    @ElementCollection
    private List<String> tags = Collections.emptyList();

    private Double price;

    private Integer pagesCount;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "article_authors")
    private List<Writer> authors;

    public Article(String title, Integer publishedYear, List<String> tags, Double price, Integer pagesCount, List<Writer> authors) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.tags = tags;
        this.price = price;
        this.pagesCount = pagesCount;
        this.authors = authors;
    }
}
