package com.example.exception;

public class AccountDoesNotExistException extends RuntimeException{
  public AccountDoesNotExistException(String message){
    super(message);
  }
  
}
