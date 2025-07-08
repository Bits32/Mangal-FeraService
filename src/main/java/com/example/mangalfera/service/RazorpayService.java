package com.example.mangalfera.service;

import com.example.mangalfera.model.PaymentRequest;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import java.util.UUID;

@Service
public class RazorpayService {
    private static final String RAZORPAY_KEY = "<your_key_id>";
    private static final String RAZORPAY_SECRET = "<your_key_secret>";

    public String createRazorpayOrder(PaymentRequest paymentRequest) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);

        JSONObject options = new JSONObject();
        options.put("amount", paymentRequest.getAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", UUID.randomUUID().toString());
        options.put("payment_capture", true);
        Order order = client.orders.create(options);
        return order.toString();
    }
}
