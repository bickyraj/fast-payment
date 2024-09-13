package com.bicky.demopayment.frontendservice.frontend.entrypoint.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fast-payment/payment")
public class PaymentController {
    @GetMapping
    public String payment(Model model) {
        return "payment";
    }
}
