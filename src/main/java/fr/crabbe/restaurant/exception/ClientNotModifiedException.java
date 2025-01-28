package fr.crabbe.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Client not modified")
public class ClientNotModifiedException extends RuntimeException {
    public ClientNotModifiedException() {
        super("Warning : Client not modified");
    }

    public ClientNotModifiedException(String message) {
        super(message);
    }
}
