package fr.crabbe.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Dish not modified")
public class DishNotModifiedException extends RuntimeException {
    public DishNotModifiedException() {
        super("Warning : Dish not modified");
    }

    public DishNotModifiedException(String message) {
        super(message);
    }
}
