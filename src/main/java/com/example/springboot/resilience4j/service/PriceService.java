package com.example.springboot.resilience4j.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.resilience4j.dto.PriceResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PriceService {

    private final RestTemplate restTemplate;

    @Value("${pricing-service.base-url:http://localhost:8080}")
    private String pricingServiceBaseUrl;

    @Retry(name = "priceService")
    @CircuitBreaker(name = "priceService", fallbackMethod = "getPriceFallback")
    public PriceResponse getPrice(Long productId) {

        String url = pricingServiceBaseUrl + "/api/prices/" + productId;

        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        return response.getBody();
    }

    /**
     * Fallback method must have same parameters plus Throwable as last parameter.
     */
    public PriceResponse getPriceFallback(Long productId, Throwable throwable) {
        // Return cached / default / last-known price
        BigDecimal defaultPrice = BigDecimal.valueOf(0.00);

        log.warn("Fallback triggered for productId={} due to {}: {}", productId,
                throwable.getClass().getSimpleName(), throwable.getMessage());

        return new PriceResponse(productId, defaultPrice, "HKD");
    }
}