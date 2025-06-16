package com.example.mangalfera.repository;

import com.example.mangalfera.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            Long senderId1, Long receiverId1, Long senderId2, Long receiverId2
    );

    List<Message> findByReceiverIdOrderByTimestampDesc(Long receiverId);
}
