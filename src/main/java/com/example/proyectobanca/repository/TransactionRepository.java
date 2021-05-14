package com.example.proyectobanca.repository;

import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByBankAccount(Long bankAccount);

}
