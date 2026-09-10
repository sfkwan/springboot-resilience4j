package com.example.springboot.resilience4j.controller;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.PriceResponse;
import com.example.springboot.resilience4j.exception.DownstreamPricingException;

@RestController
@RequestMapping("/api/prices")
public class FakePricingController {

    private final Random random = new Random();

    @GetMapping("/{id}")
    public PriceResponse getPrice(@PathVariable Long id) {

        int value = random.nextInt(10);

        if (value < 2) {
            // 30% of the time: simulate slow response
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new DownstreamPricingException("Pricing request was interrupted",
                        exception);
            }
        }

        if (value > 7) {
            // 20% of the time: simulate failure
            throw new DownstreamPricingException("Downstream pricing service failed");
        }

        return new PriceResponse(id, BigDecimal.valueOf(99.99), "USD");
    }
}