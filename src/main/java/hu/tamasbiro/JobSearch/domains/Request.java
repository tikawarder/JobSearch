package hu.tamasbiro.JobSearch.domains;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    @Size(min=1)
    @Size(max=50)
    private String description;
    @Size(min=1)
    @Size(max=50)
    private String location;
}
