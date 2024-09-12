package com.bicky.demopayment.paymentservice.payment.entrypoint.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/payment")
public class PaymentViewController {

    @GetMapping("/add-payment-method")
    @PermitAll
    public String getAddPaymentMethodForm(Model model) {
        return "add-payment-method";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Payment service running";
    }
}
