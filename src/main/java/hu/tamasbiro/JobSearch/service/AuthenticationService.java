package hu.tamasbiro.JobSearch.service;

import hu.tamasbiro.JobSearch.domains.Client;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthenticationService {
    private Client loggedInClient;
    private String uuid = "empty";

    public Client getLoggedInClient() {
        return loggedInClient;
    }

    public boolean checkUUID(String uuid){
        return this.uuid.equals(uuid);
    }

    public String login(String username, String email) {
        String uuid = UUID.randomUUID().toString();
        this.loggedInClient = Client
                .builder()
                .id(uuid)
                .name(username)
                .email(email)
                .build();
        this.uuid = uuid;
        return uuid;
    }
}
