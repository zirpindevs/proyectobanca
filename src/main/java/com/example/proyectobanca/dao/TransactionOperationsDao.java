package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TransactionOperationsDao {

    List<Transaction> getDailyBalanceByDateRangeByNumAccount(Long idBankAccount, Map<String, String> map1);

    List getTotalTransactionsByDateRangeByNumAccount(Long idBankAccount, Map<String, String> map1);

    List<Transaction> getDailyTransactionByDateRangeByCreditCard(Long idCreditCard, Map<String, String> map1);

    List<Transaction> getAllOperationsByCategory(Long idBankAccount, Map<String, String> map1);


    }
