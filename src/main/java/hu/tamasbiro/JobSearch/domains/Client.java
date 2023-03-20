package hu.tamasbiro.JobSearch.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Client {
    @Id
    private String uuid;
    @Size(min=1)
    @Size(max=100)
    private String name;
    @Email
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
