package hu.tamasbiro.JobSearch.services;

import hu.tamasbiro.JobSearch.domains.Position;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class UrlService {
    public static final String PATH = "/{id}";

    public String createUrlWithId(Position createdPosition){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
                .buildAndExpand(createdPosition).toUri();
        return location.toString();
    }
}
