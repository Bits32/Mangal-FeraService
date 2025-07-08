package com.example.mangalfera.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private int amount;
    private String name;
    private String email;
    private String contact;
}
