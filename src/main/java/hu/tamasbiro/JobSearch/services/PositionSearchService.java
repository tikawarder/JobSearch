package hu.tamasbiro.JobSearch.services;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionSearchService {
    @Autowired
    private PositionRepository repository;

    public List<Position> searchPositions(String descriptionKeyword, String locationKeyword){
        List<Position> positionsContainsDescriptionKeyword = repository.findByDescriptionContaining(descriptionKeyword);
        List<Position> positionsContainsLocationKeyword = repository.findByLocationContaining(locationKeyword);
        positionsContainsDescriptionKeyword.retainAll(positionsContainsLocationKeyword);
        return positionsContainsDescriptionKeyword;
    }
}
