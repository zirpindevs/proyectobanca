package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionOperationsDao;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;
import com.example.proyectobanca.service.TransactionOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        Collection result = this.transactionOperationsDao.getDailyBalanceByDateRangeByNumAccount(map1);
        List<DailyBalance> dailyBalances = transformResultToDailyBalances(result);

        return dailyBalances;
    }

    private List<DailyBalance> transformResultToDailyBalances(Collection result){

        List<DailyBalance> dailyBalances = new ArrayList<DailyBalance>();

/*        result.forEach(
                item -> {
                    DailyBalance balance = new DailyBalance();
                    System.out.println(item);

                    balance.setDate(LocalDate.parse(item.getClass().getName()));
                    balance.setBalance(item.getClass().getName());
                    dailyBalances.add(balance);
                }
        );*/
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {

            DailyBalance balance = new DailyBalance();


            Object objeto = iterator.next();
            objeto.getClass().arrayType();



         //   balance.setDate(LocalDate.parse(objeto));

            System.out.println("objeto:"+objeto);
            if (objeto != null) {
                System.out.println("       clase="+objeto.getClass().getName());
            }
        }


       /* result.stream().map(item -> {
            item.getClass().g
        });*/


        return dailyBalances;
    }
}
