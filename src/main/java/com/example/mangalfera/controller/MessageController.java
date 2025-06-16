package com.example.mangalfera.controller;

import com.example.mangalfera.model.Message;
import com.example.mangalfera.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message saved = messageService.sendMessage(message);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/between")
    public List<Message> getMessagesBetween(@RequestParam Long senderId, @RequestParam Long receiverId) {
        return messageService.getMessagesBetween(senderId, receiverId);
    }

    @PostMapping("/mark-read/{id}")
    public void markMessageAsRead(@PathVariable Long id) {
        messageService.markAsRead(id);
    }

    @GetMapping("/received/{receiverId}")
    public List<Message> getMessagesByReceiver(@PathVariable Long receiverId) {
        return messageService.getMessagesByReceiverId(receiverId);
    }
}
