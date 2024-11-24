package com.bicky.demopayment.notificationservice.entrypoint.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        try {
            t1.join();  // The main thread waits for t1 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
