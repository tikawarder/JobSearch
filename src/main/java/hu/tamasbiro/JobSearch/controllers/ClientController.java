package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    public static final String ERROR_MESSAGE = "Name field must be between 1-100 chars, email should be valid " +
            "address.";
    @Autowired
    AuthenticationService service;
    @PostMapping
    public ResponseEntity<String> postClient(@Valid @RequestBody Client client){
        String uuid = service.login(client.getName(), client.getEmail());
        return ResponseEntity.ok(uuid);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleValidationExceptions(Exception ex) {
        return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
