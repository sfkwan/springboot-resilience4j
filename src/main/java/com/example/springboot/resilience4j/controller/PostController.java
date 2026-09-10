package com.example.springboot.resilience4j.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.PostResponse;
import com.example.springboot.resilience4j.service.PostService;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public PostResponse[] getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit) {
        return service.getPosts(page, limit);
    }
}