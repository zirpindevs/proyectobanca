package com.example.proyectobanca.service;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.CreditCard;

import java.util.List;

public interface BankAccountService {
    BankAccount createBankAccount(BankAccount bankAccount);
    BankAccount updateBankAccount(BankAccount modifiedBankAccount);
    void deleteBankAccount(BankAccount bankAccountToDelete);

    BankAccount findOne(Long id);
    List<BankAccount> findAll();
}
