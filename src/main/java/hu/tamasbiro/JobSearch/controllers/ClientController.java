package hu.tamasbiro.JobSearch.controllers;

import hu.tamasbiro.JobSearch.domains.Client;
import hu.tamasbiro.JobSearch.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    AuthenticationService service;
    @PostMapping
    public ResponseEntity<String> postClient(@RequestBody Client client){
        String uuid = service.login(client.getName(), client.getEmail());
        return ResponseEntity.ok(uuid);
    }
}
