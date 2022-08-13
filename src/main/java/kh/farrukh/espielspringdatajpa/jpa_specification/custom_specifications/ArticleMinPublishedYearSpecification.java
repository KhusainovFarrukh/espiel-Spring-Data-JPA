package kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications;

import kh.farrukh.espielspringdatajpa.jpa_specification.Article;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ArticleMinPublishedYearSpecification implements Specification<Article> {

    private Integer minYear;

    @Override
    public Predicate toPredicate(
            Root<Article> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (minYear == null) return null;
        return criteriaBuilder.greaterThanOrEqualTo(root.get("publishedYear"), minYear);
    }
}
