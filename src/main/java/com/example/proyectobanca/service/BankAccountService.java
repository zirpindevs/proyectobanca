package com.example.proyectobanca.service;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.BankAccountDTO;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.model.CreditCardDTO;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    List<BankAccount> findAll();

    BankAccount findOne(Long id);

    BankAccount createOne(BankAccountDTO bankAccountDTO);

    BankAccount updateOne(Long id, BankAccountDTO bankAccountDTO);

    Optional<Boolean> deleteOne(Long id);

}
