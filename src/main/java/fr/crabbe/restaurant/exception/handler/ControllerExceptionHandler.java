package fr.crabbe.restaurant.exception.handler;

import fr.crabbe.restaurant.domain.ErrorMessage;
import fr.crabbe.restaurant.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> ClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_FOUND);
        em.getErrors().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
    }

    @ExceptionHandler(ClientNotModifiedException.class)
    public ResponseEntity<ErrorMessage> ClientNotModifiedException(ClientNotModifiedException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_MODIFIED);
        em.getWarnings().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(em);
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<ErrorMessage> DishNotFoundException(DishNotFoundException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_FOUND);
        em.getErrors().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
    }

    @ExceptionHandler(DishInOrderException.class)
    public ResponseEntity<ErrorMessage> DishInOrderException(DishInOrderException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.CONFLICT);
        em.getErrors().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(em);
    }

    @ExceptionHandler(DishNotModifiedException.class)
    public ResponseEntity<ErrorMessage> DishNotModifiedException(DishNotModifiedException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_MODIFIED);
        em.getWarnings().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(em);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> OrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_FOUND);
        em.getErrors().add(ex.getMessage());
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
    }

}
