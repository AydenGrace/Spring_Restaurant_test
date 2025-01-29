package fr.crabbe.restaurant.exception.handler;

import fr.crabbe.restaurant.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> ClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ClientNotModifiedException.class)
    public ResponseEntity<String> ClientNotModifiedException(ClientNotModifiedException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<String> DishNotFoundException(DishNotFoundException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DishInOrderException.class)
    public ResponseEntity<String> DishInOrderException(DishInOrderException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DishNotModifiedException.class)
    public ResponseEntity<String> DishNotModifiedException(DishNotModifiedException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> OrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
