package com.kamilsmolarek.autofix.payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepositoryMock implements PaymentRepository {

    private final Map<String, Payment> database = new HashMap<>();

    @Override
    public void save(Payment payment) {
        database.put(payment.getId(), payment);
    }

    @Override
    public Payment findById(String id) {
        return database.get(id);
    }
}

