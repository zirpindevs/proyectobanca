package com.example.proyectobanca.dao.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionOperationsDaoImpl implements TransactionOperationsDao {

    @PersistenceContext
    private EntityManager manager;

    private final Logger log = LoggerFactory.getLogger(TransactionOperationsDaoImpl.class);

    /**
     * Dao:
     * Get the Balance per day and the Total Transactions between two dates for a bank account
     * @param idBankAccount Id of bank account that you have get the balance
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return List of the balance per day of transactions between two dates from database
     */
    @Override
    public List<Transaction> getDailyBalanceByDateRangeByNumAccount(Long idBankAccount, Map<String, String> map1) {


        try {
            if (map1.get("startDate") != null && map1.get("endDate") != null) {

                Query queryNative = manager.createNativeQuery(
                        "SELECT t.created_date, t.balance_after_transaction from `transactions` t INNER JOIN (SELECT MAX(`created_date`) as max FROM transactions WHERE `created_date` BETWEEN '"
                                + map1.get("startDate") + "'"
                                + " AND '" + map1.get("endDate") + "'"
                                + " AND `id_bank_account` = " + idBankAccount
                                + " GROUP BY date(created_date))t2 on t.created_date=t2.max ORDER BY `t`.`created_date` DESC "
                );
                List result = queryNative.getResultList();

                return result;
            }
            return new ArrayList<>();

        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * DAO:
     * Get the Total of Transactions per day between two dates for a bank account
     * @param idBankAccount Id of bank account that you want to get the total of transactions
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return List of the Total of Transactions between two dates from database
     */
    @Override
    public List getTotalTransactionsByDateRangeByNumAccount(Long idBankAccount, Map<String, String> map1) {
        try {
            if (map1.get("startDate") != null && map1.get("endDate") != null) {

                Query queryNative = manager.createNativeQuery(
                        "SELECT COUNT(id) AS totalTransactions, DATE(`created_date`) FROM `transactions` WHERE `created_date` BETWEEN '"
                                + map1.get("startDate") + "'"
                                + " AND '" + map1.get("endDate") + "'"
                                + " AND `id_bank_account` = " + idBankAccount
                                + " GROUP BY DATE(`created_date`) ORDER BY DATE(`created_date`) DESC"
                );
                List result = queryNative.getResultList();

                return result;
            }
            return new ArrayList<>();

        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }


    //*************************************************************************************************************


    /**
     * Dao:
     * Get the number of trabsaction per day  between two dates for a creditcard.
     * @param idCreditCard Id of creditcard account that you have get the balance
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return List of the balance per day of transactions between two dates from database
     */
    @Override
    public List<Transaction> getDailyTransactionByDateRangeByCreditCard(Long idCreditCard, Map<String, String> map1) {

/*
SELECT COUNT(id) AS totalTransactions, DATE(`created_date`) FROM `transactions` WHERE `created_date` BETWEEN '2021-02-11 00:00:15.000000' AND '2021-04-11 23:59:15.000000' GROUP BY DATE(`created_date`)
 */
        try {
            if (map1.get("startDate") != null && map1.get("endDate") != null) {

                Query queryNative = manager.createNativeQuery(
                        "SELECT COUNT(`id`) AS totalTransactions, DATE(`created_date`) from `transactions` WHERE `created_date` BETWEEN '"
                                + map1.get("startDate") + "'"
                                + " AND '" + map1.get("endDate") + "'"
                                + " AND `id_credit_card` = " + idCreditCard
                                + " GROUP BY DATE(`created_date`) DESC "
                );
                List result = queryNative.getResultList();

                return result;
            }
            return new ArrayList<>();

        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }

    }

}
