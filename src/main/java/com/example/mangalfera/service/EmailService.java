package com.example.mangalfera.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    DynamicMailConfigService dynamicMailConfigService;

    public void sendEmail(String to, String subject, String from, String body) {
        try{
            JavaMailSender dynamicMailSender = dynamicMailConfigService.getActiveMailSender();
            MimeMessage mimeMessage = dynamicMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            dynamicMailSender.send(mimeMessage);
        }catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
