package com.example.springboot.resilience4j.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User returned by the user management API")
public record UserResponse(
        @Schema(description = "Unique identifier of the user", example = "123e4567-e89b-12d3-a456-426614174000") UUID id,
        @Schema(description = "User's display name", example = "Jane Doe") String name,
        @Schema(description = "User's email address", example = "jane.doe@example.com") String email) {
}