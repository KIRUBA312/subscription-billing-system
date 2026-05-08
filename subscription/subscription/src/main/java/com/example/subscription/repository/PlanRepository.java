package com.example.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
