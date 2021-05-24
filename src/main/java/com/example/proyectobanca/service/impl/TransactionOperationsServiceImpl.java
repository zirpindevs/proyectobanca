package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.transaction.operations.DailyBalanceRange;
import com.example.proyectobanca.model.transaction.operations.DailyBalanceResponse;
import com.example.proyectobanca.model.transaction.operations.totalOperations.DailyOperationsRange;
import com.example.proyectobanca.model.transaction.operations.totalOperations.DailyOperationsResponse;
import com.example.proyectobanca.model.transaction.operations.totalTransactions.DailyTransactionRange;
import com.example.proyectobanca.model.transaction.operations.totalTransactions.DailyTransactionResponse;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.repository.CreditCardRepository;
import com.example.proyectobanca.service.TransactionOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionOperationsServiceImpl implements TransactionOperationsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final TransactionOperationsDao transactionOperationsDao;

    private final BankAccountRepository bankAccountRepository;

    private final CreditCardRepository creditCardRepository;

    public TransactionOperationsServiceImpl(TransactionOperationsDao transactionOperationsDao, BankAccountRepository bankAccountRepository, CreditCardRepository creditCardRepository) {
        this.transactionOperationsDao = transactionOperationsDao;
        this.bankAccountRepository = bankAccountRepository;
        this.creditCardRepository = creditCardRepository;
    }

    /**
     * Service:
     * Get the Balance and Total of Transactions per day between two dates for a bank account
     * @param idBankAccount Id of bank account that you have get the balance
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyBalanceResponse with List of the Balance and Total of Transactions per day between two dates from database
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

            DailyBalanceResponse dailyBalanceResponse = new DailyBalanceResponse();

            dailyBalanceResponse = transformResultToDailyBalances(result, map1, dailyBalanceResponse);

            result = this.transactionOperationsDao.getTotalTransactionsByDateRangeByNumAccount(idBankAccount, map1);

            dailyBalanceResponse = transformResultToDailyBalances(result, map1, dailyBalanceResponse);

            return dailyBalanceResponse;

        }catch (Exception e){

            log.error(e.getMessage());
            return new DailyBalanceResponse("-500");
        }
    }

    /**
     * Transform the result of the database into a response of type DailyBalanceResponse
     * @param result List of the balance and Total of Transactions per day between two dates from database
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyBalanceResponse with List of the balance and Total of Transactions per day between two dates from database
     */
    private DailyBalanceResponse transformResultToDailyBalances(List result, Map<String, String> map1, DailyBalanceResponse dailyBalanceResponse) {

        if (dailyBalanceResponse.getStatus() == null){
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
            dailyBalanceResponse.setStatus("ok");
        }else{
            AtomicReference<Integer> i = new AtomicReference<>(0);

            result.forEach(
                    item -> {

                        BigInteger totalTransactions = (BigInteger) ((Object[]) item)[0];
                        dailyBalanceResponse.getDailyBalanceRanges().get(i.get()).setTotalTransactions(totalTransactions);
                        i.getAndSet(i.get() + 1);
                    }
            );
        }

        return dailyBalanceResponse;
    }
































































































































































    //*********************************************************************************************************


    /**
     * Service:
     * Get the number of transactions per day between two dates for a creditcard
     * @param idCreditCard Id of creditcard that you have get the transactions
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyTransactionResponse with List of transactions by day of transactions between two dates from database
     */
    @Override
    public DailyTransactionResponse getDailyTransactionByDateRangeByCreditCard(Long idCreditCard, Map<String, String> map1) {

        try {

            boolean creditCardExist = creditCardRepository.existsById(idCreditCard);
            if (!creditCardExist)
                return new DailyTransactionResponse("-404");


            List result = this.transactionOperationsDao.getDailyTransactionByDateRangeByCreditCard(idCreditCard, map1);

            if (result.size() == 0)
                return new DailyTransactionResponse("-204");

            DailyTransactionResponse dailyTransactionResponse = transformResultToDailyTransaction(result, map1);

            return dailyTransactionResponse;

        }catch (Exception e){

            log.error(e.getMessage());
            return new DailyTransactionResponse("-500");
        }
    }

    /**
     * Transform the result of the database into a response of type DailyBalanceResponse
     * @param result List of the balance per day of transactions between two dates from database
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyTransactionResponse with List of the balance per day of transactions between two dates from database
     */
    private DailyTransactionResponse transformResultToDailyTransaction(List result, Map<String, String> map1) {

        DailyTransactionResponse dailyTransactionResponse = new DailyTransactionResponse("ok");

        dailyTransactionResponse.setStartDate(map1.get("startDate"));
        dailyTransactionResponse.setEndDate( map1.get("endDate"));


        result.forEach(
                item -> {
                    DailyTransactionRange transaction = new DailyTransactionRange();

                    Object transactionDate = ((Object[]) item)[1];
                    BigInteger totalTransactions = (BigInteger) ((Object[]) item)[0];

                    transaction.setTransactionDate(transactionDate.toString());
                    transaction.setTotalTransactions(totalTransactions);
                    dailyTransactionResponse.getDailyTransactionRanges().add(transaction);
                }
        );

        return dailyTransactionResponse;
    }

    /**
     * Service:
     * Get the number of operations per day between two dates for a bank account
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyOperationsResponse with List of the operations per day by category between two dates from database
     */
    @Override
    public DailyOperationsResponse getAllOperationsByCategory(Long idBankAccount, Map<String, String> map1) {

        try {

            boolean bankAccountExist = bankAccountRepository.existsById(idBankAccount);
            if (!bankAccountExist)
                return new DailyOperationsResponse("-404");


            List result = this.transactionOperationsDao.getAllOperationsByCategory(idBankAccount, map1);

            if (result.size() == 0)
                return new DailyOperationsResponse("-204");

            DailyOperationsResponse dailyOperationsResponse = transformResultToDailyOperation(result, map1);

            return dailyOperationsResponse;

        }catch (Exception e){

            log.error(e.getMessage());
            return new DailyOperationsResponse("-500");
        }
    }


    /**
     * Transform the result of the database into a response of type DailyOperationsResponse
     * @param result List of operations per day in a bankaccount between two dates from database
     * @param map1 Map<String, String> with DateRange params and pagination optionals params
     * @return DailyOperationsResponse with List of the operations per day in a bankaccount between two dates from database
     */
    private DailyOperationsResponse transformResultToDailyOperation(List result, Map<String, String> map1) {

        DailyOperationsResponse dailyOperationsResponse = new DailyOperationsResponse("ok");

        dailyOperationsResponse.setStartDate(map1.get("startDate"));
        dailyOperationsResponse.setEndDate( map1.get("endDate"));


        result.forEach(
                item -> {
                    DailyOperationsRange operation = new DailyOperationsRange();

                    BigInteger category = (BigInteger) ((Object[]) item)[0];
                    BigInteger totalTransactions = (BigInteger) ((Object[]) item)[1];
                    Object transactionDate = ((Object[]) item)[2];

                    operation.setCategory(category);
                    operation.setTotalOperations(totalTransactions);
                    operation.setOperationDate(transactionDate.toString());
                    dailyOperationsResponse.getDailyOperationsRanges().add(operation);
                }
        );

        return dailyOperationsResponse;
    }

}
