package com.example.springboot.resilience4j.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springboot.resilience4j.dto.PostResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class JsonplaceholderPostService {
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplate restTemplate;

    public PostResponse[] getPosts(int page, int limit) {
        String url = UriComponentsBuilder.fromUriString(POSTS_URL)
                .queryParam("_page", page + 1)
                .queryParam("_limit", limit)
                .toUriString();

        return restTemplate.getForObject(url, PostResponse[].class);
    }
}