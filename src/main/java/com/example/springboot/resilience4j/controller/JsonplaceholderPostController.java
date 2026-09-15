package com.example.springboot.resilience4j.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.PostResponse;
import com.example.springboot.resilience4j.service.JsonplaceholderPostService;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Posts", description = "Endpoints for retrieving posts from JSONPlaceholder")
public class JsonplaceholderPostController {
    private final JsonplaceholderPostService service;

    @GetMapping
    @Operation(summary = "Get posts", description = "Retrieves a page of posts from the JSONPlaceholder downstream service.")
    public PostResponse[] getAll(
            @Parameter(description = "Zero-based page number", example = "0", schema = @io.swagger.v3.oas.annotations.media.Schema(minimum = "0")) @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(description = "Maximum number of posts to return", example = "20", schema = @io.swagger.v3.oas.annotations.media.Schema(minimum = "1", maximum = "100")) @RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit) {
        return service.getPosts(page, limit);
    }
}