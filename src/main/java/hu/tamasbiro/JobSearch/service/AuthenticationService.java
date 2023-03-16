package hu.tamasbiro.JobSearch.service;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    ClientRepository clientRepository;

    public Optional<String> login(String username, String password) {
        final String uuid = UUID.randomUUID().toString();
        final Client client = Client
                .builder()
                .id(uuid)
                .name(username)
                .email(password)
                .build();

        clientRepository.save(client);
        return Optional.of(uuid);
    }

    public void logout(final Client user) {

    }
}
