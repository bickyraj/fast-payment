package com.bicky.demopayment.notificationservice.entrypoint.rest.controller;

import com.bicky.demopayment.notificationservice.dto.NotificationRequestBody;
import com.bicky.demopayment.notificationservice.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<String> home(HttpServletRequest request) {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/send-message")
    public ResponseEntity<String> sendMessage() {
        notificationService.sendMessage();
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/create-message")
    public ResponseEntity<String> createMessage(@RequestBody NotificationRequestBody notificationRequestBody) {
        return ResponseEntity.ok("Message sent");
    }
}
