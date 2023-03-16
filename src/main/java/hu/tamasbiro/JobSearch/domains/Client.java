package hu.tamasbiro.JobSearch.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Client {
    @Id
    private String id;
    private String name;
    private String email;
}
