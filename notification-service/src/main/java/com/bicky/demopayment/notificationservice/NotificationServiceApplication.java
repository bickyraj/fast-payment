package com.bicky.demopayment.notificationservice;

import com.bicky.demopayment.notificationservice.service.NotificationService;
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
        System.out.println("hello news world");
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerSendMessage() {
//        notificationService.sendMessage();
//    }
}
