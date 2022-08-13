package kh.farrukh.espielspringdatajpa.jpa_specification;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticleFilterParams {

    private Integer minimumPublishedYear;
    private Integer maximumPublishedYear;
    private List<String> tags;
    private List<Long> authorIds;
}
