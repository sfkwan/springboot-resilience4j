package com.example.springboot.resilience4j.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product pricing information")
public record PriceResponse(
        @Schema(description = "Unique identifier of the product", example = "42") Long productId,
        @Schema(description = "Current product price", example = "99.99") BigDecimal price,
        @Schema(description = "ISO 4217 currency code", example = "USD") String currency) {
}