package com.example.proyectobanca.service;

import com.example.proyectobanca.model.User;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;

import java.util.List;
import java.util.Map;

public interface TransactionOperationsService {

    List<DailyBalance> getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1);
}
