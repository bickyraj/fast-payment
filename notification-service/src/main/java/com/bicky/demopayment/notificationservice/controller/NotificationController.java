package com.bicky.demopayment.notificationservice.controller;

import com.bicky.demopayment.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/send-message")
    public ResponseEntity<String> sendMessage() {
        notificationService.sendMessage();
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        notificationService.getUserId();
        return ResponseEntity.ok("Test");
    }
}
