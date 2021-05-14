package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.Transaction;

public interface TransactionDAO {
    Transaction findById(Long id);

}
