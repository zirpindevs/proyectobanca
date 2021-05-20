package com.example.proyectobanca.dao;

import com.example.proyectobanca.model.Transaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TransactionOperationsDao {

    List<Transaction> getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1);
}
