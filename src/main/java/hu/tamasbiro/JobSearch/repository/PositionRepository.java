package hu.tamasbiro.JobSearch.repository;

import hu.tamasbiro.JobSearch.domains.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
