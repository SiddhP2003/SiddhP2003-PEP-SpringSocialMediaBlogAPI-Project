package com.example.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler (DuplicateUsernameException.class)
  public ResponseEntity<Void> handleDuplicate() {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler (InvalidRequestException.class)
  public ResponseEntity<Void> handleInvalid() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @ExceptionHandler (AccountDoesNotExistException.class)
  public ResponseEntity<Void> handleAccountDoesNotExist() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @ExceptionHandler (IncorrectPasswordException.class)
  public ResponseEntity<Void> handleIncorrectPassword() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
