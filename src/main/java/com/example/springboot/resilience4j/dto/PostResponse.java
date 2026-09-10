package com.example.springboot.resilience4j.dto;

public record PostResponse(Long userId, Long id, String title, String body) {
}