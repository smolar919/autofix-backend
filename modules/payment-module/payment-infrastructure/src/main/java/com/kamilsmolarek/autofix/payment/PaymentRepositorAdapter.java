package com.kamilsmolarek.autofix.payment;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositorAdapter implements PaymentRepository{
    private final PaymentRepositoryJpa jpaRepository;

    public PaymentRepositorAdapter(PaymentRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Payment payment) {
        PaymentEntity entity = PaymentMapper.toEntity(payment);
        jpaRepository.save(entity);
    }

    @Override
    public Payment findById(String id) {
        return jpaRepository.findById(id).map(PaymentMapper::toPayment).orElse(null);
    }
}
