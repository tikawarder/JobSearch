package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.exceptions.AuthenticationException;
import hu.tamasbiro.JobSearch.repositories.PositionRepository;
import hu.tamasbiro.JobSearch.services.AuthenticationService;
import hu.tamasbiro.JobSearch.services.PositionSearchService;
import hu.tamasbiro.JobSearch.services.UrlService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/position")
@Validated
public class PositionController {
    private static final String ERROR_MESSAGE = "Description and Locations fields must be between 1-50 chars.";
    private static final String NOT_CORRECT_UUID = "not correct UUID";
    private static final String UUID_KEY = "uuid";
    private static final String PATHVARIABLE_ID = "idOfPosition";
    @Autowired
    private PositionRepository repository;
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UrlService urlService;
    @Autowired
    private PositionSearchService searchService;

    @PostMapping
    public ResponseEntity<String> postPosition(@Valid @RequestBody Position request,
                                               @RequestHeader(UUID_KEY) String uuid){
        if(authService.isUUIDIncorrect(uuid)) throw new AuthenticationException(NOT_CORRECT_UUID);
        Position position = Position.builder()
                .description(request.getDescription())
                .location(request.getLocation())
                .build();
        Position createdPosition = repository.save(position);
        return ResponseEntity.ok(urlService.createUrlWithId(createdPosition));
    }

    @GetMapping("/id/{idOfPosition}")
    public ResponseEntity<Position> getPositionById (@PathVariable(name= PATHVARIABLE_ID) long id){
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> getURLsOfPositionsContainsDescriptionAndLocation(@RequestParam @Size(min=1,
            max=50) String positionKeyWord,
            @RequestParam @Size(min=1, max=50) String locationKeyWord,
            @RequestHeader(UUID_KEY) String uuid){
        if(authService.isUUIDIncorrect(uuid)) throw new AuthenticationException(NOT_CORRECT_UUID);
        List<Position> searchedPositions = searchService.searchPositions(positionKeyWord, locationKeyWord);
        return ResponseEntity.ok(searchedPositions.stream()
                .map(position -> urlService.createUrlWithId(position))
                .collect(Collectors.toList()));
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    private ResponseEntity<String> handleValidationExceptions(Exception ex) {
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
