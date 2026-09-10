package com.example.springboot.resilience4j.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2–50 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
