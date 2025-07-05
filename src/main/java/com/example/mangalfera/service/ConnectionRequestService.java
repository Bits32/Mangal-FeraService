package com.example.mangalfera.service;

import com.example.mangalfera.model.ConnectionRequest;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.ConnectionRequestRepository;
import com.example.mangalfera.repository.ProfileRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConnectionRequestService {

    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    public void sendRequest(Long receiverId) {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile sender = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sender profile not found"));

        Profile receiver = profileRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver profile not found"));

        if (connectionRequestRepository.existsBySenderIdAndReceiverId(sender.getId(), receiver.getId())) {
            return;
        }

        ConnectionRequest request = new ConnectionRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());

        connectionRequestRepository.save(request);
    }

    public void respondToRequest(Long senderId, String responseStatus) {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile receiver = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sender profile not found"));

        ConnectionRequest request = connectionRequestRepository.findBySenderIdAndReceiverId(senderId, receiver.getId());
        if (request == null) throw new RuntimeException("Request not found");

        request.setStatus(responseStatus);
        request.setRespondedAt(LocalDateTime.now());

        connectionRequestRepository.save(request);
    }

    public List<ConnectionRequest> getMyReceivedRequests() {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile receiver = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sender profile not found"));

        return connectionRequestRepository.findByReceiverIdAndStatus(receiver.getId(), "PENDING");
    }

    public List<ConnectionRequest> getMySentRequests() {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile sender = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sender profile not found"));

        return connectionRequestRepository.findBySenderId(sender.getId());
    }
}
