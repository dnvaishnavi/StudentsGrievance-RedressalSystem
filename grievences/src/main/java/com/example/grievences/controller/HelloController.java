package com.example.grievences.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "🎉 Spring Boot is running successfully!";
    }

    @GetMapping("/test")
    public String test() {
        return "✅ API test successful!";
    }
}
