package com.example.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
