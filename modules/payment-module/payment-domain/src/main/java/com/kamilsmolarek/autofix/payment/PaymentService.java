package com.kamilsmolarek.autofix.payment;

public interface PaymentService {
    Payment initiateVisibilityPayment(CreatePaymentForm form);
}