package com.kamilsmolarek.autofix.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoryJpa extends JpaRepository<PaymentEntity, String> {
}
