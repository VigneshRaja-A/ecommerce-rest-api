package com.example.E_Commerce.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
            public ResponseEntity<Map<String,String>> handleUserExists(UserAlreadyExistsException ex)
    {
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFound(UsernameNotFoundException ex)
    {
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleItemNotFound(ItemNotFoundException ex)
    {
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleProductExists(ProductAlreadyExistsException ex)
    {
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach(error ->{
           String fieldName = ((FieldError)error) .getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName,errorMessage);
       });
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, String> error = new HashMap<>();

        error.put("error", "Invalid email or password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ReviewAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleReviewAlreadyExistException(ReviewAlreadyExistException ex)
    {
        Map<String,String> error = new HashMap<>();
        error.put("error: ",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Map<String,String>> handleMissingTransaction(InvalidDataAccessApiUsageException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("Error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
