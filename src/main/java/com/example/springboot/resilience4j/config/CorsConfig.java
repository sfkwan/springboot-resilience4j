package com.example.springboot.resilience4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${app.cors.allowed-origins:}")
    private String configuredOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration cors = registry.addMapping("/**");
        String[] allowedOrigins = Arrays.stream(configuredOrigins.split(","))
                .map(origin -> origin.trim())
                .filter(origin -> !origin.isEmpty())
                .toArray(String[]::new);
        if (allowedOrigins.length > 0) {
            cors.allowedOrigins(allowedOrigins);
        }
        cors
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders(HttpHeaders.CONTENT_DISPOSITION)
                .allowCredentials(false);
    }
}
