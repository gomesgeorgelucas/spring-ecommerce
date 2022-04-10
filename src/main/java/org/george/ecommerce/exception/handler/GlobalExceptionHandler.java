package org.george.ecommerce.exception.handler;

import org.george.ecommerce.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

record Error(
        String message,
        String description
) {}

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
        return ResponseEntity.status(403).body(new Error(e.getMessage(), "Error"));
    }

    @ExceptionHandler({InvalidRequestException.class, })
    public ResponseEntity<Error> handleInvalidRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Invalid Request.", "Error"));
    }

    @ExceptionHandler({NotFoundException.class, })
    public ResponseEntity<Error> handleNotFound(NotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(ex.getMessage(), "Error"));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, })
    public ResponseEntity<Error> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(ex.getMessage(), "Error"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
