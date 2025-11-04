package com.ramonov.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public String isHealth() {
        return "Welcome to RAMONOV, everything is fine!";
    }
}
