package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.transaction.operations.DailyBalanceRange;
import com.example.proyectobanca.model.transaction.operations.DailyBalanceResponse;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.TransactionOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TransactionOperationsServiceImpl implements TransactionOperationsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final TransactionOperationsDao transactionOperationsDao;

    private final BankAccountRepository bankAccountRepository;

    public TransactionOperationsServiceImpl(TransactionOperationsDao transactionOperationsDao, BankAccountRepository bankAccountRepository) {
        this.transactionOperationsDao = transactionOperationsDao;
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Service:
     * Get the balance per day of transactions between two dates for a bank account.
     * @param idBankAccount Id of bank account that you have get the balance
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyBalanceResponse with List of the balance per day of transactions between two dates from database
     */
    @Override
    public DailyBalanceResponse getDailyBalanceByDateRangeByNumAccount(Long idBankAccount, Map<String, String> map1) {

        try {

            boolean bankAccountExist = bankAccountRepository.existsById(idBankAccount);
            if (!bankAccountExist)
                return new DailyBalanceResponse("-404");


            List result = this.transactionOperationsDao.getDailyBalanceByDateRangeByNumAccount(idBankAccount, map1);

            if (result.size() == 0)
                return new DailyBalanceResponse("-204");

            DailyBalanceResponse dailyBalanceResponse = transformResultToDailyBalances(result, map1);

            return dailyBalanceResponse;

        }catch (Exception e){

            log.error(e.getMessage());
            return new DailyBalanceResponse("-500");
        }
    }

    /**
     * Transform the result of the database into a response of type DailyBalanceResponse
     * @param result List of the balance per day of transactions between two dates from database
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyBalanceResponse with List of the balance per day of transactions between two dates from database
     */
    private DailyBalanceResponse transformResultToDailyBalances(List result, Map<String, String> map1) {

        DailyBalanceResponse dailyBalanceResponse = new DailyBalanceResponse("ok");

        result.forEach(
                item -> {
                    DailyBalanceRange balance = new DailyBalanceRange();

                    Object[] transactionDate = ((Object[]) item);
                    Double balanceDay = (Double) ((Object[]) item)[1];

                    balance.setStartDate(map1.get("startDate"));
                    balance.setEndDate( map1.get("endDate"));
                    balance.setDate((Timestamp) transactionDate[0]);
                    balance.setBalance(balanceDay);
                    dailyBalanceResponse.getDailyBalanceRanges().add(balance);
                }
        );

        return dailyBalanceResponse;
    }
}
