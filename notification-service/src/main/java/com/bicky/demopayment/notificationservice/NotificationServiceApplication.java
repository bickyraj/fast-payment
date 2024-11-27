package com.bicky.demopayment.notificationservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@AllArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerSendMessage() {
//        notificationService.sendMessage();
//    }
}
