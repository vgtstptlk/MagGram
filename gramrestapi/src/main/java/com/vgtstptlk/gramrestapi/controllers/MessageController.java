package com.vgtstptlk.gramrestapi.controllers;

import com.vgtstptlk.gramrestapi.domains.Message;
import com.vgtstptlk.gramrestapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping
    List<Message> getMessages(){
        return messageRepository.findAll();
    }

    @GetMapping("/from/{loginFrom}")
    List<Message> getMessageByLoginFrom(@PathVariable String loginFrom){
        return messageRepository.findAllByLoginFrom(loginFrom);
    }

    @PostMapping
    ResponseEntity<?> createMessage(@RequestParam String loginFrom, @RequestParam String loginTo, @RequestParam String caption){
        Message message = new Message(loginFrom, loginTo, caption);
        messageRepository.save(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/to/{loginTo}")
    List<Message> getMessagesByLoginTo(@PathVariable String loginTo){
        return messageRepository.findAllByLoginTo(loginTo);
    }

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
