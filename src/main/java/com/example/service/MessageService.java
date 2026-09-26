package com.example.service;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidRequestException;
import java.util.*;

@Service
public class MessageService {
  private final MessageRepository messageRepository;
  private final AccountRepository accountRepository;

  public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
    this.messageRepository = messageRepository;
    this.accountRepository = accountRepository;
  }

  public Message createMessage(Message message){
    String message_text = message.getMessageText();
    Integer postedBy = message.getPostedBy();

    if(message_text == null || message_text.isBlank() || message_text.length() >= 255 || postedBy == null || !accountRepository.existsById(postedBy)){
      throw new InvalidRequestException("Message Creation Unsuccessful");
    }

    return messageRepository.save(message);

  }

  public List<Message> getAllMessages(){
    return messageRepository.findAll();
  }

  public Message getMessage(Integer messageId){
    return messageRepository.findById(messageId).orElse(null);
  }

  public Integer deleteMessage(Integer messageId){

    if(messageRepository.existsById(messageId)){
      messageRepository.deleteById(messageId);
      return 1; 
    }
    return null;
  }

  public Integer updateMessage(Integer messageId, String messageText){
    if(messageText == null || messageText.isBlank() || messageText.length() > 255){
      throw new InvalidRequestException("Message Update Unsuccessful");
    }
    Message message = messageRepository.findById(messageId).orElseThrow(() -> new InvalidRequestException("Message does not exist"));

    message.setMessageText(messageText);
    messageRepository.save(message);
    return 1;
  }
}
