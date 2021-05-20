package com.example.proyectobanca.service;

import com.example.proyectobanca.model.Transaction;
import com.example.proyectobanca.model.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(TransactionDTO transactionDTO);
    Transaction updateTransaction(Long id, TransactionDTO transactionDTO);
    void deleteTransaction(Transaction transactionToDelete);

    Transaction findOne(Long id);
    List<Transaction> findAll();
}
