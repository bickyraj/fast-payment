package com.bicky.demopayment.notificationservice;

import com.bicky.demopayment.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
        System.out.println("hello");
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerSendMessage() {
//        notificationService.sendMessage();
//    }
}
