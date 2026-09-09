package com.example.springboot.rescilence4j.dto;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email) {
}