package com.example.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
