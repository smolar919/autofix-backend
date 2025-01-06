package com.kamilsmolarek.autofix.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentServiceTest {

    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        StripePaymentGateway paymentGateway = new StripePaymentGatewayMock();
        PaymentRepository paymentRepository = new PaymentRepositoryMock();
        paymentService = new PaymentServiceImpl(paymentGateway, paymentRepository);
    }

    @Test
    void initiateVisibilityPayment_createsPaymentSuccessfully() {
        String workshopId = "workshop123";
        int amount = 500;
        String currency = "USD";
        int months = 3;
        LocalDate expectedValidUntil = LocalDate.now().plusMonths(months);
        CreatePaymentForm form = new CreatePaymentForm(workshopId, amount, currency, months);
        Payment payment = paymentService.initiateVisibilityPayment(form);

        assertNotNull(payment);
        assertEquals(amount, payment.getAmount());
        assertEquals(currency, payment.getCurrency());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals(workshopId, payment.getWorkshopId());
        assertEquals("VISIBILITY", payment.getPurpose());
        assertEquals(expectedValidUntil, payment.getValidUntil());
    }
}
