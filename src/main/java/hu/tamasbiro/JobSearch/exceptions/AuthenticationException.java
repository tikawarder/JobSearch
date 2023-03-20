package hu.tamasbiro.JobSearch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value= HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, reason="API key in UUID format is not correct")
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String error) {
        super(error);
    }
}