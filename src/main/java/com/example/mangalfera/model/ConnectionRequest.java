package com.example.mangalfera.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ConnectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Profile sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Profile receiver;

    private String status;

    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public void setReceiver(Profile receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(LocalDateTime respondedAt) {
        this.respondedAt = respondedAt;
    }
}
