package com.example.proyectobanca.dao;

import java.util.Collection;
import java.util.Map;

public interface TransactionOperationsDao {

    Collection getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1);
}
