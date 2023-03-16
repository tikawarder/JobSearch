package hu.tamasbiro.JobSearch.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Search {
    private String descriptionKeyword;
    private String locationKeyword;
}
