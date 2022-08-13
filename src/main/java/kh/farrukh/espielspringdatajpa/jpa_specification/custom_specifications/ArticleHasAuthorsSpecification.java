package kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications;

import kh.farrukh.espielspringdatajpa.jpa_specification.Article;
import kh.farrukh.espielspringdatajpa.jpa_specification.Writer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@AllArgsConstructor
public class ArticleHasAuthorsSpecification implements Specification<Article> {

    private List<Long> authors;

    @Override
    public Predicate toPredicate(
            Root<Article> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (authors == null || authors.isEmpty()) return null;
        Join<Article, Writer> authorsJoin = root.join("authors");
        return authorsJoin.get("id").in(authors);
    }
}
