package com.example.mangalfera.controller;

import com.example.mangalfera.model.ConnectionRequest;
import com.example.mangalfera.service.ConnectionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connection-requests")
public class ConnectionRequestController {
    @Autowired
    private ConnectionRequestService connectionRequestService;

    @PostMapping("/send/{receiverId}")
    public ResponseEntity<String> sendConnectionRequest(@PathVariable Long receiverId) {
        connectionRequestService.sendRequest(receiverId);
        return ResponseEntity.ok("Connection request sent.");
    }

    @PostMapping("/respond/{senderId}")
    public ResponseEntity<String> respondToRequest(
            @PathVariable Long senderId,
            @RequestParam String status) { // ACCEPTED or REJECTED
        connectionRequestService.respondToRequest(senderId, status);
        return ResponseEntity.ok("Connection request " + status.toLowerCase() + ".");
    }

    @GetMapping("/received")
    public ResponseEntity<List<ConnectionRequest>> getReceivedRequests() {
        return ResponseEntity.ok(connectionRequestService.getMyReceivedRequests());
    }

    @GetMapping("/sent")
    public ResponseEntity<List<ConnectionRequest>> getSentRequests() {
        return ResponseEntity.ok(connectionRequestService.getMySentRequests());
    }

}
