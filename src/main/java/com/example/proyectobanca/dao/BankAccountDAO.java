package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.BankAccount;

import java.util.List;
import java.util.Map;

public interface BankAccountDAO {
    List<BankAccount> findAllByFilters(Map<String, String> map1);

    BankAccount findById(Long id);
}
