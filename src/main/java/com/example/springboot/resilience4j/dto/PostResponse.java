package com.example.springboot.resilience4j.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Post returned by the JSONPlaceholder service")
public record PostResponse(
        @Schema(description = "Identifier of the user who created the post", example = "1") Long userId,
        @Schema(description = "Unique identifier of the post", example = "1") Long id,
        @Schema(description = "Title of the post", example = "A sample post") String title,
        @Schema(description = "Body content of the post", example = "This is the post content.") String body) {
}