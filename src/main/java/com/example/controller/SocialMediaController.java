package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidRequestException;
import java.util.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
  
  private final AccountService accountService;
  private final MessageService messageService;

  public SocialMediaController(AccountService accountService, MessageService messageService){
    this.accountService = accountService;
    this.messageService = messageService;
  }

  @PostMapping("/register")
  public ResponseEntity<Account> register(@RequestBody Account newAccount){
    return ResponseEntity.ok(accountService.register(newAccount));
  }

  @PostMapping("/login")
  public ResponseEntity<Account> login(@RequestBody Account account) {
    return ResponseEntity.ok(accountService.login(account));
  }

  @PostMapping("/messages")
  public ResponseEntity<Message> createMessage(@RequestBody Message message){
    return ResponseEntity.ok(messageService.createMessage(message));
  }

  @GetMapping("/messages")
  public ResponseEntity<List<Message>> getAllMessages(){
    return ResponseEntity.ok(messageService.getAllMessages());
  }

  @GetMapping("/messages/{messageId}")
  public ResponseEntity<Message> getMessage(@PathVariable Integer messageId){
    return ResponseEntity.ok(messageService.getMessage(messageId));
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId){
    return ResponseEntity.ok(messageService.deleteMessage(messageId));
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity<Message> updateMessage(@PathVariable Integer messageId, @RequestBody Message message){
    return ResponseEntity.ok(messageService.updateMessage(messageId, message.getMessageText()));
  }

}
