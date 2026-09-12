package com.example.springboot.resilience4j.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.resilience4j.dto.UserDto;
import com.example.springboot.resilience4j.dto.UserResponse;
import com.example.springboot.resilience4j.entity.User;
import com.example.springboot.resilience4j.service.UserService;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService service;

    @GetMapping
    public Page<UserResponse> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit) {
        return service.getAllUsers(PageRequest.of(page, limit))
                .map(this::toResponse);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable UUID id) {
        return toResponse(service.getUser(id));
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserDto userDto) {
        return toResponse(service.saveUser(toUser(userDto)));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable UUID id, @Valid @RequestBody UserDto userDto) {
        User user = toUser(userDto);
        user.setId(id);
        return toResponse(service.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteUser(id);
    }

    private User toUser(UserDto userDto) {
        return new User(null, userDto.getName(), userDto.getEmail());
    }

    private UserResponse toResponse(User user) {
        return user == null ? null : new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}