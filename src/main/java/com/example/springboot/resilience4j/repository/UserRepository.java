package com.example.springboot.resilience4j.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.resilience4j.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}