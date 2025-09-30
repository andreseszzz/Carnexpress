package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Bienvenido a Spring Boot 🚀";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hola, Spring Boot está funcionando ✔️";
    }
}
