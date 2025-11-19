package com.ramonov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/health")
public class RestHealthController {

    @Autowired
    private Optional<BuildProperties> build;
    @GetMapping
    public String isHealth() {
        String version = build.map(BuildProperties::getVersion).orElse("unknown");
        return "Welcome to Ramonov version: " + version +  ", everything is fine!";
    }
}
