package com.example.springboot.resilience4j.controller;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.springboot.resilience4j.dto.PriceResponse;
import com.example.springboot.resilience4j.service.PriceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Endpoints for product-related operations, including retrieving product pricing through a resilient service.")

public class ProductController {

    private final PriceService priceService;

    @GetMapping("/{id}/price")
    @Operation(summary = "Get a product price", description = "Retrieves the price for a product through the resilient pricing service.")
    public PriceResponse getProductPrice(
            @Parameter(description = "Unique identifier of the product", required = true, example = "42") @PathVariable Long id) {
        return priceService.getPrice(id);
    }
}
