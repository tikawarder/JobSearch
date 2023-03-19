package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.domains.Request;
import hu.tamasbiro.JobSearch.repository.PositionRepository;
import hu.tamasbiro.JobSearch.service.AuthenticationService;
import hu.tamasbiro.JobSearch.service.PositionSearchService;
import hu.tamasbiro.JobSearch.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    PositionRepository repository;
    @Autowired
    AuthenticationService authService;
    @Autowired
    UrlService urlService;
    @Autowired
    PositionSearchService searchService;

    @GetMapping("/id/{idOfPosition}")
    public ResponseEntity<Position> getPositionById (@PathVariable(name="idOfPosition") long id){
        return ResponseEntity.of(repository.findById(id)); //toDo: care about if no value returned
    }

    @GetMapping("/search")
    public ResponseEntity<List<Position>> getURLsBySearch(@RequestParam String positionKeyWord, @RequestParam String locationKeyWord){
        return ResponseEntity.ok(searchService.searchPositions(positionKeyWord, locationKeyWord));
    }

    @PostMapping
    public ResponseEntity<String> postPosition(@RequestBody Request request){
        if(authService.checkUUID(request.getUUID())) {
            Position position = Position.builder()
                    .description(request.getDescription())
                    .location(request.getLocation())
                    .build();
            Position createdPosition = repository.save(position);
            return ResponseEntity.ok(urlService.createUrlWithId(createdPosition));
        }
        else return ResponseEntity.ofNullable("UUID is not good");
    }
}
