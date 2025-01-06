package com.kamilsmolarek.autofix.payment;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/visibility")
    public Payment initiateVisibilityPayment(@Valid @RequestBody CreatePaymentForm form) {
        return paymentService.initiateVisibilityPayment(form);
    }
}