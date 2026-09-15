package com.example.springboot.resilience4j.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.UserDto;
import com.example.springboot.resilience4j.dto.UserResponse;
import com.example.springboot.resilience4j.entity.User;
import com.example.springboot.resilience4j.service.UserService;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "List users", description = "Returns a paginated list of users.")
    public Page<UserResponse> getAll(
            @Parameter(description = "Zero-based page number", example = "0", schema = @io.swagger.v3.oas.annotations.media.Schema(minimum = "0")) @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(description = "Number of users to return per page", example = "20", schema = @io.swagger.v3.oas.annotations.media.Schema(minimum = "1", maximum = "100")) @RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit) {
        return service.getAllUsers(PageRequest.of(page, limit))
                .map(this::toResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user", description = "Returns a user by its unique identifier.")
    public UserResponse getById(
            @Parameter(description = "Unique identifier of the user", required = true, example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID id) {
        return toResponse(service.getUser(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user", description = "Creates a new user and returns the saved user.")
    public UserResponse create(@Valid @RequestBody UserDto userDto) {
        return toResponse(service.saveUser(toUser(userDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Updates an existing user and returns the saved user.")
    public UserResponse update(
            @Parameter(description = "Unique identifier of the user", required = true, example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User details to save", required = true) @Valid @RequestBody UserDto userDto) {
        User user = toUser(userDto);
        user.setId(id);
        return toResponse(service.saveUser(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes the user with the specified unique identifier.")
    public void delete(
            @Parameter(description = "Unique identifier of the user", required = true, example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID id) {
        service.deleteUser(id);
    }

    private User toUser(UserDto userDto) {
        return new User(null, userDto.getName(), userDto.getEmail());
    }

    private UserResponse toResponse(User user) {
        return user == null ? null : new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}