package com.example.proyectobanca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {


    // ALL
    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }

    // ONLY AUTHENTICATED
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
