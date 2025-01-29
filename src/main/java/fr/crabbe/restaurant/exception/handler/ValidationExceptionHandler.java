package fr.crabbe.restaurant.exception.handler;

import fr.crabbe.restaurant.domain.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//        StringBuilder messageFields = new StringBuilder();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.FORBIDDEN);
        for (ObjectError allError : ex.getAllErrors()) {
            errorMessage.getErrors().add(allError.getDefaultMessage());
            System.out.println("Error on validation : "+allError.getDefaultMessage());
//            if(messageFields.isEmpty()) messageFields.append(allError.getDefaultMessage());
//            else messageFields.append(", ").append(allError.getDefaultMessage());
        }

//        messageFields = new StringBuilder("[" + messageFields + "]");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
//        return new ResponseEntity<>("Error : "+messageFields, HttpStatus.FORBIDDEN);
    }
}
