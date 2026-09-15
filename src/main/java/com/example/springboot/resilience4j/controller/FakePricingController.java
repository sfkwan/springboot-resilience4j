package com.example.springboot.resilience4j.controller;

import java.math.BigDecimal;
import java.util.Random;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.springboot.resilience4j.dto.PriceResponse;
import com.example.springboot.resilience4j.exception.DownstreamPricingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/prices", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Pricing", description = "Endpoints for retrieving simulated product pricing")
public class FakePricingController {

    private final Random random = new Random();

    @GetMapping("/{id}")
    @Operation(summary = "Get a product price", description = "Returns a simulated price for the requested product. The downstream pricing failure is intentionally simulated for resilience testing.")
    public PriceResponse getPrice(
            @Parameter(description = "Unique identifier of the product", required = true, example = "42") @PathVariable Long id) {

        log.warn("FakePricingController.getPrice has been called with {}", id);
        int value = random.nextInt(20);

        // if (value < 2) {
        // // 30% of the time: simulate slow response
        // try {
        // Thread.sleep(500);
        // } catch (InterruptedException exception) {
        // Thread.currentThread().interrupt();
        // throw new DownstreamPricingException("Pricing request was interrupted",
        // exception);
        // }
        // }

        if (value > 5) {
            // 20% of the time: simulate failure
            throw new DownstreamPricingException("Downstream pricing service failed");
        }

        return new PriceResponse(id, BigDecimal.valueOf(99.99), "USD");
    }
}