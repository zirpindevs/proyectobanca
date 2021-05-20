package com.example.proyectobanca.dao.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionOperationsDaoImpl implements TransactionOperationsDao {

    @PersistenceContext
    private EntityManager manager;

    private final Logger log = LoggerFactory.getLogger(TransactionOperationsDaoImpl.class);

    @Override
    public Collection getDailyBalanceByDateRangeByNumAccount(Map<String, String> map1) {


        try {
            if (map1.get("startDate") != null && map1.get("endDate") != null) {

                Query queryNative = manager.createNativeQuery(
                        "SELECT t.created_date, t.balance_after_transaction from `transactions` t INNER JOIN (SELECT MAX(`created_date`) as max FROM transactions WHERE `created_date` BETWEEN '"
                                + map1.get("startDate") + "'"
                                + " AND '" + map1.get("endDate") + "'"
                                + " GROUP BY date(created_date))t2 on t.created_date=t2.max ORDER BY `t`.`created_date` DESC"
                );
                Collection result = queryNative.getResultList();

                return result;
            }
            return (List<DailyBalance>) new DailyBalance();

        }catch (Exception e){
            log.error(e.getMessage());
            return (List<DailyBalance>) new DailyBalance();
        }

    }






























































































































































































    //********************************************************************************************
}
