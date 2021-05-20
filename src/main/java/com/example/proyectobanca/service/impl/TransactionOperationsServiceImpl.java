package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;
import com.example.proyectobanca.service.TransactionOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionOperationsServiceImpl implements TransactionOperationsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final TransactionOperationsDao transactionOperationsDao;

    public TransactionOperationsServiceImpl(TransactionOperationsDao transactionOperationsDao) {
        this.transactionOperationsDao = transactionOperationsDao;
    }


    @Override
    public List<DailyBalance> getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1) {
        return this.transactionOperationsDao.getDailyBalanceByDateRangeByNumAccount(map1);
    }
}
