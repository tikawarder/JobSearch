package hu.tamasbiro.JobSearch.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String location;

    public Position(long id, String description, String location) {
        this.id = id;
        this.description = description;
        this.location = location;
    }

    public Position() {
    }
}
