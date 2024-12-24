package com.vgdb.vgdb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class RootController {

    /**
     * Root endpoint that confirms the API is online.
     *
     * @return Welcome message
     */
    @GetMapping("/")
    public ResponseEntity<String> rootEndpoint() {
        return ResponseEntity.ok("Welcome to VGDB API! The API is online.");
    }

    /**
     * Health check endpoint to confirm service status.
     *
     * @return Map with service status and version
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "online");
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }

    /**
     * Documentation placeholder endpoint.
     *
     * @return Message about where to find API docs
     */
    @GetMapping("/docs")
    public ResponseEntity<String> docsEndpoint() {
        return ResponseEntity.ok("Visit /docs for API documentation (not implemented yet).");
    }
}
