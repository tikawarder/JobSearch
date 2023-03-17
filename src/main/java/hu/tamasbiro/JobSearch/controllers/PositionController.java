package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Position;
import hu.tamasbiro.JobSearch.repository.PositionRepository;
import hu.tamasbiro.JobSearch.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    PositionRepository repository;
    @Autowired
    EntityLinks entityLinks;
    @Autowired
    AuthenticationService authService;

    @GetMapping("/id/{idOfPosition}")
    public ResponseEntity<Position> getPositionById (@PathVariable(name="idOfPosition") long id){
        return ResponseEntity.of(repository.findById(id)); //toDo: care about if no value returned
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> getURLsBySearch(@RequestParam String positionKeyWord, @RequestParam String locationKeyWord){
        List<String> urlList = new ArrayList<>();
        urlList.add("www.google.com"); //toDO call service to add urls
        return ResponseEntity.ok(urlList);
    }

    @PostMapping
    public ResponseEntity<String> postPosition(@RequestBody Position position){
            Position createdPosition = repository.save(position);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") //toDo: look after is id needed?
                    .buildAndExpand(createdPosition).toUri();
            return ResponseEntity.ok(location.toString());

    }
}
