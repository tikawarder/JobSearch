package hu.tamasbiro.JobSearch.repository;

import hu.tamasbiro.JobSearch.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
