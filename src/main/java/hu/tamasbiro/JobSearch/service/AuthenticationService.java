package hu.tamasbiro.JobSearch.service;

import hu.tamasbiro.JobSearch.domains.Client;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthenticationService {

    private Client loggedInClient;

    public Client getLoggedInClient() {
        return loggedInClient;
    }

    public boolean checkUUID(){
        return this.loggedInClient.getId() != null;
    }

    public String login(String username, String email) {
        String uuid = UUID.randomUUID().toString();
        this.loggedInClient = Client
                .builder()
                .id(uuid)
                .name(username)
                .email(email)
                .build();
        return uuid;
    }
}
