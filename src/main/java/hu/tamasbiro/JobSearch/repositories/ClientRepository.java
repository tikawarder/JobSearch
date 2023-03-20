package hu.tamasbiro.JobSearch.repositories;

import hu.tamasbiro.JobSearch.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByNameAndEmail(String name, String email);
}
