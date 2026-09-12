package com.example.springboot.resilience4j.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.resilience4j.entity.User;
import com.example.springboot.resilience4j.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repo;

    public Page<User> getAllUsers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public User getUser(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}
