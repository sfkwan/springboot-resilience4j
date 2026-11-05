package com.example.springboot.resilience4j.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "Download", description = "Endpoints for download file")
public class DownloadController {
    @GetMapping("/api/download")
    public ResponseEntity<ByteArrayResource> downloadFile() {

        byte[] data = "Hello World".getBytes();

        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"sample.txt\"")
                // .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                // HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(data.length)
                .body(resource);
    }
}
