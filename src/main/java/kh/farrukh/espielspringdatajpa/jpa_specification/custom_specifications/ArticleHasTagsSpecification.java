package kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications;

import kh.farrukh.espielspringdatajpa.jpa_specification.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@AllArgsConstructor
public class ArticleHasTagsSpecification implements Specification<Article> {

    private List<String> tags;

    @Override
    public Predicate toPredicate(
            Root<Article> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (tags == null || tags.isEmpty()) return null;
        Join<Article, String> tagsJoin = root.join("tags");
        return tagsJoin.in(tags);
    }
}
