package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.BankAccount;

public interface BankAccountDAO {
    BankAccount findById(Long id);
}
