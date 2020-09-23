package com.ecommerce.orders.dao;

import com.ecommerce.orders.domain.Item;
import com.ecommerce.orders.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
