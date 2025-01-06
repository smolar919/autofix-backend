package com.kamilsmolarek.autofix.payment;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final StripePaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(StripePaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment initiateVisibilityPayment(CreatePaymentForm form) {
        Payment payment = paymentGateway.createPayment(form.getAmount(), form.getCurrency());

        LocalDate validUntil = LocalDate.now().plusMonths(form.getMonths());

        Payment fullPayment = new Payment(
                payment.getId(),
                form.getAmount(),
                form.getCurrency(),
                payment.getClientSecret(),
                PaymentStatus.PENDING,
                form.getWorkshopId(),
                "VISIBILITY",
                validUntil
        );

        paymentRepository.save(fullPayment);
        return fullPayment;
    }
}
