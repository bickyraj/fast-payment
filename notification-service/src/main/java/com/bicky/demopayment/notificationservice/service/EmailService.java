package com.bicky.demopayment.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bickyrajkayastha@gmail.com");
        message.setSubject("Spring boot email test");
        message.setText("hello how are you");
        message.setFrom("hello@gmail.com");
        javaMailSender.send(message);
    }

}
