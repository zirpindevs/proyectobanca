package com.example.proyectobanca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

/*
ENDPOINTS

Usuarios:
•	Get all
•	Get one by ID
•	Get by Filters ?
            •	Create User / SingUp
•	Update by ID
•	Delete by ID
*/

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
