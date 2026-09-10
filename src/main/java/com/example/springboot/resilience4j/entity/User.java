package com.example.springboot.resilience4j.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36, nullable = false)
    private UUID id;

    private String name;
    private String email;

    // getters and setters
}
