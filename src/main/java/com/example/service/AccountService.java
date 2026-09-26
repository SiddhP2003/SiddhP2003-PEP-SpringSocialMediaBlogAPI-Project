package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidRequestException;
import com.example.exception.AccountDoesNotExistException;
import com.example.exception.IncorrectPasswordException;
import com.example.repository.AccountRepository;
import com.example.entity.Account;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }

  public Account findAccountByUsername(String username){
    return accountRepository.findAccountByUsername(username);
  }

  public Account register(Account newAccount){
    String username = newAccount.getUsername();
    String password = newAccount.getPassword();
    
    if(username == null || username.isBlank() || password == null || password.length() < 4){
      throw new InvalidRequestException("Invalid username or password");
    }

    if(accountRepository.findAccountByUsername(username) != null){
      throw new DuplicateUsernameException("Username already exists");
    }

    return accountRepository.save(newAccount);
  }

  public Account login(Account account){
    String username = account.getUsername();
    String password = account.getPassword();

    if (username == null || password == null){
      throw new AccountDoesNotExistException("Invalid username or password");
    }

    Account storedAccount = accountRepository.findAccountByUsername(account.getUsername());

    if(storedAccount == null){
      throw new AccountDoesNotExistException("Username does not exist");
    }

    if(!storedAccount.getPassword().equals(password)){
      throw new IncorrectPasswordException("Invalid password");
        }

    return storedAccount;

  }
}
