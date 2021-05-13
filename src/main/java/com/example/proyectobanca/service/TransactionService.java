package com.example.proyectobanca.service;

import com.example.proyectobanca.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction modifiedTransaction);
    void deleteTransaction(Transaction transactionToDelete);

    Transaction findOne(Long id);
    List<Transaction> findAll();
}
