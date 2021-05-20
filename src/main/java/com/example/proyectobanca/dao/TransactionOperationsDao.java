package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.transaction.operations.DailyBalance;

import java.util.List;
import java.util.Map;

public interface TransactionOperationsDao {

    List<DailyBalance> getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1);
}
