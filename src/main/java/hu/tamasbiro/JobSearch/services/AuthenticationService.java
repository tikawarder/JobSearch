package hu.tamasbiro.JobSearch.services;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private Client loggedInClient;
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    public void setLoggedInClient(Client loggedInClient) {
        this.loggedInClient = loggedInClient;
    }

    @Autowired
    private ClientRepository repository;

    public boolean isUUIDIncorrect(String uuid){
        return loggedInClient == null || !this.loggedInClient.getUuid().equals(uuid);
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
