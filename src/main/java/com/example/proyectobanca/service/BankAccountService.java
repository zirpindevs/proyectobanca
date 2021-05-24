package com.example.proyectobanca.service;

import com.example.proyectobanca.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BankAccountService {

    List<BankAccount> findAll(Map<String, String> map1);

    Optional<BankAccount> findOne(Long id);

    BankAccount createOne(BankAccountDTO bankAccountDTO);

    BankAccount updateOne(Long id, BankAccountDTO bankAccountDTO);

    Optional<Boolean> deleteOne(Long id);

}
