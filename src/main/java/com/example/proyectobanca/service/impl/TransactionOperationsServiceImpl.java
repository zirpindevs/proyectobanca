package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.Transaction;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;
import com.example.proyectobanca.service.TransactionOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class TransactionOperationsServiceImpl implements TransactionOperationsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final TransactionOperationsDao transactionOperationsDao;

    public TransactionOperationsServiceImpl(TransactionOperationsDao transactionOperationsDao) {
        this.transactionOperationsDao = transactionOperationsDao;
    }


    @Override
    public List<DailyBalance> getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1) {

        List result = this.transactionOperationsDao.getDailyBalanceByDateRangeByNumAccount(map1);
        List<DailyBalance> dailyBalances = transformResultToDailyBalances(result, map1);

        return dailyBalances;
    }

    private List<DailyBalance> transformResultToDailyBalances(List result, Map<String, String> map1) {

        List<DailyBalance> dailyBalances = new ArrayList<DailyBalance>();

        result.forEach(
                item -> {
                    DailyBalance balance = new DailyBalance();

                    Object[] transactionDate = ((Object[]) item);
                    Double balanceDay = (Double) ((Object[]) item)[1];

                    balance.setStartDate(map1.get("startDate"));
                    balance.setEndDate( map1.get("endDate"));
                    balance.setDate((Timestamp) transactionDate[0]);
                    balance.setBalance(balanceDay);
                    dailyBalances.add(balance);
                }
        );

        return dailyBalances;

     
    }
}
