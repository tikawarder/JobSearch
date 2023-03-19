package hu.tamasbiro.JobSearch.repository;

import hu.tamasbiro.JobSearch.domains.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByDescriptionContaining(String description);
    List<Position> findByLocationContaining(String location);
    List<Position> findByDescriptionAndLocationContaining(String description, String location);

//    @Query("SELECT u FROM Position u WHERE u.Decription LIKE u and u.Location LIKE u")
//    List<Position> findByDescriptionAndLoacation (String description, String Location);
}