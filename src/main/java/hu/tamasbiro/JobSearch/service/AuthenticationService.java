package hu.tamasbiro.JobSearch.service;

import hu.tamasbiro.JobSearch.domains.Client;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthenticationService {
    public static final String EMPTY_UUID = "empty_uuid";
    private Client loggedInClient = new Client(EMPTY_UUID);
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    public boolean isUUIDIncorrect(String uuid){
        return !this.loggedInClient.getUuid().equals(uuid);
    }

    public String login(String username, String email) {
        String uuid = UUID.randomUUID().toString();
        this.loggedInClient = Client
                .builder()
                .uuid(uuid)
                .name(username)
                .email(email)
                .build();
        return uuid;
    }
}
