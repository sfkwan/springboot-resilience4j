package com.example.springboot.resilience4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.PriceResponse;
import com.example.springboot.resilience4j.service.PriceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final PriceService priceService;

    @GetMapping("/{id}/price")
    public PriceResponse getProductPrice(@PathVariable Long id) {
        return priceService.getPrice(id);
    }
}
