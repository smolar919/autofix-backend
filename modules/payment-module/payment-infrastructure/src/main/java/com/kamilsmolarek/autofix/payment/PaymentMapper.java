package com.kamilsmolarek.autofix.payment;

public class PaymentMapper {

    public static Payment toPayment(PaymentEntity entity) {
        return Payment.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .clientSecret(entity.getClientSecret())
                .status(entity.getStatus())
                .workshopId(entity.getWorkshopId())
                .purpose(entity.getPurpose())
                .validUntil(entity.getValidUntil())
                .build();
    }

    public static PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .clientSecret(payment.getClientSecret())
                .status(payment.getStatus())
                .workshopId(payment.getWorkshopId())
                .purpose(payment.getPurpose())
                .validUntil(payment.getValidUntil())
                .build();
    }
}