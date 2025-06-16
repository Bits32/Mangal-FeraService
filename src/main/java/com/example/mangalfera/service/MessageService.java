package com.example.mangalfera.service;

import com.example.mangalfera.model.Message;
import com.example.mangalfera.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repo;

    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return repo.save(message);
    }

    public List<Message> getMessagesBetween(Long senderId, Long receiverId) {
        return repo.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(senderId, receiverId, senderId, receiverId);
    }

    public void markAsRead(Long id) {
        Message msg = repo.findById(id).orElseThrow();
        msg.setRead(true);
        repo.save(msg);
    }

    public List<Message> getMessagesByReceiverId(Long receiverId) {
        return repo.findByReceiverIdOrderByTimestampDesc(receiverId);
    }
}
