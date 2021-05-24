package com.example.proyectobanca.service;

import com.example.proyectobanca.model.transaction.operations.DailyBalanceRange;
import com.example.proyectobanca.model.transaction.operations.DailyBalanceResponse;
import com.example.proyectobanca.model.transaction.operations.totalTransactions.DailyTransactionResponse;

import java.util.List;
import java.util.Map;

public interface TransactionOperationsService {

    DailyBalanceResponse getDailyBalanceByDateRangeByNumAccount(Long id, Map<String, String> map1);
    DailyTransactionResponse getDailyTransactionByDateRangeByCreditCard(Long idCreditCard, Map<String, String> map1);

    }
