package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.exceptions.AuthenticationException;
import hu.tamasbiro.JobSearch.repository.PositionRepository;
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
    public static final String ERROR_MESSAGE = "Description and Locations fields must be between 1-50 chars.";
    public static final String NOT_CORRECT_UUID = "not correct UUID";
    @Autowired
    PositionRepository repository;
    @Autowired
    AuthenticationService authService;
    @Autowired
    UrlService urlService;
    @Autowired
    PositionSearchService searchService;

    @PostMapping
    public ResponseEntity<String> postPosition(@Valid @RequestBody Position request,
                                               @RequestHeader("uuid") String uuid){
        if(authService.isUUIDIncorrect(uuid)) throw new AuthenticationException(NOT_CORRECT_UUID);
        Position position = Position.builder()
                .description(request.getDescription())
                .location(request.getLocation())
                .build();
        Position createdPosition = repository.save(position);
        return ResponseEntity.ok(urlService.createUrlWithId(createdPosition));
    }

    @GetMapping("/id/{idOfPosition}")
    public ResponseEntity<Position> getPositionById (@PathVariable(name="idOfPosition") long id){
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> getURLsBySearch(@RequestParam @Size(min=1, max=50) String positionKeyWord,
                                                        @RequestParam @Size(min=1, max=50) String locationKeyWord,
                                                        @RequestHeader("uuid") String uuid){
        if(authService.isUUIDIncorrect(uuid)) throw new AuthenticationException(NOT_CORRECT_UUID);
        List<Position> searchedPositions = searchService.searchPositions(positionKeyWord, locationKeyWord);
        return ResponseEntity.ok(searchedPositions.stream()
                .map(position -> urlService.createUrlWithId(position))
                .collect(Collectors.toList()));
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    ResponseEntity<String> handleValidationExceptions(Exception ex) {
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
