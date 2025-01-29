package fr.crabbe.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dish not found")
public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException() {
        super("Dish not found");
    }

    public DishNotFoundException(String message) {
        super(message);
    }
}
