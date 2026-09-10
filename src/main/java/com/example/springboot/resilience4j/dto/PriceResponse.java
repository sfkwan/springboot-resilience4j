package com.example.springboot.resilience4j.dto;

import java.math.BigDecimal;

public record PriceResponse(
                Long productId,
                BigDecimal price,
                String currency) {
}