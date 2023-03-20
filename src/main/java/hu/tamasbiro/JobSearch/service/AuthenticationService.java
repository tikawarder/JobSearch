package hu.tamasbiro.JobSearch.service;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    public static final String EMPTY_UUID = "empty_uuid";
    private Client loggedInClient = new Client(EMPTY_UUID);
    public Client getLoggedInClient() {
        return loggedInClient;
    }
    @Autowired
    ClientRepository repository;

    public boolean isUUIDIncorrect(String uuid){
        return !this.loggedInClient.getUuid().equals(uuid);
    }

    public String login(Client client) {
        Optional<Client> existingClient = repository.findByNameAndEmail(client.getName(), client.getEmail());
        if(existingClient.isPresent()){
            this.loggedInClient = existingClient.get();
            return existingClient.get().getUuid();
        }
        return createNewClientWithRandomUuid(client);

    }

    private String createNewClientWithRandomUuid(Client client){
        String uuid = UUID.randomUUID().toString();
        Client newClient = Client
                .builder()
                .uuid(uuid)
                .name(client.getName())
                .email(client.getEmail())
                .build();
        this.loggedInClient = repository.save(newClient);
        return uuid;
    }
}
