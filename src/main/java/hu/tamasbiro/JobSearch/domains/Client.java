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
    private String uuid;
    private String name;
    private String email;

    public Client(String uuid) {
        this.uuid = uuid;
    }

    public Client(String uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }
}
