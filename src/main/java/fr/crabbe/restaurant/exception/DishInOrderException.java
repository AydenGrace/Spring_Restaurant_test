package fr.crabbe.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Dish already exist in an Order")
public class DishInOrderException extends RuntimeException {
  public DishInOrderException() {
    super("Error : Dish already exist in an Order");
  }
    public DishInOrderException(String message) {
        super(message);
    }
}
