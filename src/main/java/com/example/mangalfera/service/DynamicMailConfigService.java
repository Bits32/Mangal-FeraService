package com.example.mangalfera.service;

import com.example.mangalfera.model.MailConfig;
import com.example.mangalfera.repository.MailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class DynamicMailConfigService {

    @Autowired
    MailConfigRepository mailConfigRepository;
    public JavaMailSender getActiveMailSender() {
        Optional<MailConfig> mailConfigOpt = mailConfigRepository.findByActiveTrue();

        if (mailConfigOpt.isEmpty()) {
            throw new IllegalArgumentException("No active email configuration found");
        }

        return buildJavaMailSender(mailConfigOpt.get());
    }

    private JavaMailSender buildJavaMailSender(MailConfig mailConfig) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfig.getHost());
        javaMailSender.setPort(mailConfig.getPort());
        javaMailSender.setUsername(mailConfig.getUsername());
        javaMailSender.setPassword(mailConfig.getPassword());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", mailConfig.getAuth());
        props.put("mail.smtp.starttls.enable", mailConfig.getStarttls());

        return javaMailSender;
    }
}
