package kh.farrukh.espielspringdatajpa.jpa_specification.specification_utils;

import kh.farrukh.espielspringdatajpa.jpa_specification.Article;
import kh.farrukh.espielspringdatajpa.jpa_specification.ArticleFilterParams;
import kh.farrukh.espielspringdatajpa.jpa_specification.Writer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.List;

public class ArticleSpecifications {

    public static Specification<Article> publishedYearGreaterThanOrEqualTo(Integer minYear) {
        return (root, query, criteriaBuilder) -> {
            if (minYear == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("publishedYear"), minYear);
        };
    }

    public static Specification<Article> publishedYearLessThanOrEqualTo(Integer maxYear) {
        return (root, query, criteriaBuilder) -> {
            if (maxYear == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("publishedYear"), maxYear);
        };
    }

    public static Specification<Article> hasTag(List<String> tags) {
        return (root, query, criteriaBuilder) -> {
            if (tags == null || tags.isEmpty()) return null;
            Join<Article, String> tagsJoin = root.join("tags");
            query.distinct(true);
            return tagsJoin.in(tags);
        };
    }

    public static Specification<Article> hasAuthor(List<Long> authorIds) {
        return (root, query, criteriaBuilder) -> {
            if (authorIds == null || authorIds.isEmpty()) return null;
            Join<Article, Writer> tagsJoin = root.join("authors");
            query.distinct(true);
            return tagsJoin.get("id").in(authorIds);
        };
    }

    public static Specification<Article> filterBy(ArticleFilterParams params) {
        return Specification
                .where(publishedYearGreaterThanOrEqualTo(params.getMinimumPublishedYear()))
                .and(publishedYearLessThanOrEqualTo(params.getMaximumPublishedYear()))
                .and(hasTag(params.getTags()))
                .and(hasAuthor(params.getAuthorIds()));
    }
}
