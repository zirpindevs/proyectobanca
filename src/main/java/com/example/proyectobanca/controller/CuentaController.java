package com.example.proyectobanca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CuentaController {

/* ENDPOINTS

    Cuentas:
            •	Get all
•	Get one by ID
•	Get by Filters:
    o	Get all by User
    o	Get Ingresos/gastos by Fecha
•	Create Cuenta
•	Update by ID
•	Delete by ID

*/


    @GetMapping("/cuenta")
    public String home(){
        return "Welcome to cuenta.";
    }
}
