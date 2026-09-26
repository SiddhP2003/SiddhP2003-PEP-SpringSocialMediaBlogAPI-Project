package com.example.service;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

@Service
@RequestMapping("/messages")
public class MessageService {
  private final MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message createMessage(){
    return null;
  }
}
