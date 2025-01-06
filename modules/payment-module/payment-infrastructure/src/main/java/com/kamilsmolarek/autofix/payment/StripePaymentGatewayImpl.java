package com.kamilsmolarek.autofix.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.stripe.Stripe.apiKey;

@Component
public class StripePaymentGatewayImpl implements StripePaymentGateway{

    public StripePaymentGatewayImpl(@Value("${stripe.secretKey}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    public Payment createPayment(int amount, String currency) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) amount * 100)
                    .setCurrency(currency)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return new Payment(
                    paymentIntent.getId(),
                    amount,
                    currency,
                    paymentIntent.getClientSecret(),
                    PaymentStatus.PENDING,
                    null,
                    null,
                    null
            );
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create payment with Stripe", e);
        }
    }
}
