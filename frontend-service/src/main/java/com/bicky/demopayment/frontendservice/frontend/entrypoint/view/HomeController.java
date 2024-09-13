package com.bicky.demopayment.frontendservice.frontend.entrypoint.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fast-payment")
public class HomeController {
    @GetMapping
    public String hello() {
        return "hello";
    }
}
