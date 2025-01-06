package com.kamilsmolarek.autofix.payment;

public interface StripePaymentGateway {
    Payment createPayment(int amount, String currency);
}
