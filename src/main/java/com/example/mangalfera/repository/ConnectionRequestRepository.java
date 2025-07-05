package com.example.mangalfera.repository;

import com.example.mangalfera.model.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
    List<ConnectionRequest> findByReceiverIdAndStatus(Long receiverId, String status);
    List<ConnectionRequest> findBySenderId(Long senderId);

    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);
    ConnectionRequest findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
