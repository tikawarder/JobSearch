package hu.tamasbiro.JobSearch.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private String UUID;
    private String description;
    private String location;
}
