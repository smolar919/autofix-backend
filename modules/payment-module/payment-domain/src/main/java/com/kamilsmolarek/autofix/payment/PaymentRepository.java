package com.kamilsmolarek.autofix.payment;

public interface PaymentRepository {
    void save(Payment payment);

    Payment findById(String id);
}
