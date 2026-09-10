package com.example.springboot.resilience4j.exception;

public class DownstreamPricingException extends RuntimeException {

    public DownstreamPricingException(String message) {
        super(message);
    }

    public DownstreamPricingException(String message, Throwable cause) {
        super(message, cause);
    }
}