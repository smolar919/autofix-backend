package com.kamilsmolarek.autofix.payment;

import java.util.UUID;

public class StripePaymentGatewayMock implements StripePaymentGateway {

    @Override
    public Payment createPayment(int amount, String currency) {
        return new Payment(
                UUID.randomUUID().toString(),
                amount,
                currency,
                "mock_client_secret_" + UUID.randomUUID(),
                PaymentStatus.PENDING,
                null,
                null,
                null
        );
    }
}
