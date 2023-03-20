package hu.tamasbiro.JobSearch.services;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionSearchServiceTest {
    public static final String DESCRIPTION = "Developer";
    public static final String LOCATION = "New York";
    @Mock
    PositionRepository repository;
    @InjectMocks
    PositionSearchService underTest;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchPositionsReturnsUnionOfLists(){
        //given
        List<Position> searchDescription = fillDescriptionSearch();
        List<Position> searchLocation = fillLocationSearch();
        List<Position> expectedPositions = new ArrayList<>(Arrays.asList(Position.builder()
                .description(DESCRIPTION)
                .location(LOCATION)
                .build()));
        //when
        Mockito.when(repository.findByDescriptionContaining(DESCRIPTION)).thenReturn(searchDescription);
        Mockito.when(repository.findByLocationContaining(LOCATION)).thenReturn(searchLocation);
        List<Position> results = underTest.searchPositions(DESCRIPTION, LOCATION);
        //then
        assertEquals(expectedPositions, results);
        Mockito.verify(repository).findByDescriptionContaining(DESCRIPTION);
        Mockito.verify(repository).findByLocationContaining(LOCATION);
    }

    private List<Position> fillDescriptionSearch(){
        Position position1 = Position.builder()
                .description(DESCRIPTION)
                .location(LOCATION)
                .build();
        Position position2 = Position.builder()
                .description("Python Developer")
                .build();
        return new ArrayList<>(Arrays.asList(position1, position2));
    }

    private List<Position> fillLocationSearch(){
        Position position1 = Position.builder()
                .location("New York")
                .build();
        Position position2 = Position.builder()
                .description(DESCRIPTION)
                .location(LOCATION)
                .build();
        return new ArrayList<>(Arrays.asList(position1, position2));
    }
}
