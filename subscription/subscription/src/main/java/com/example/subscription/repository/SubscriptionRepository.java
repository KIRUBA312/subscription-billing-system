package com.example.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
