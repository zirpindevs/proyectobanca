package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.TransactionDAO;
import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.Transaction;
import com.example.proyectobanca.model.User;
import com.example.proyectobanca.repository.TransactionRepository;
import com.example.proyectobanca.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger log = LoggerFactory.getLogger(Transaction.class);

    private final TransactionRepository transactionRepository;
    private final TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionDAO transactionDAO) {
        this.transactionRepository = transactionRepository;
        this.transactionDAO = transactionDAO;
    }


    @Override
    public Transaction createTransaction(Transaction transaction) {
        log.debug("Create Transaction: {}", transaction);

        Transaction transactionCreated = null;

        try{
            Optional<Transaction> checkTransaction = transactionRepository.findById(transaction.getId());

            if (!ObjectUtils.isEmpty(checkTransaction))
                return new Transaction();

            BankAccount bankAccountTest = new BankAccount();
            bankAccountTest.setEnabled(true);
            bankAccountTest.setNumAccount(1111L);
            bankAccountTest.setBalance(3D);
            transaction.setBankAccount(bankAccountTest);
            System.out.println(transaction);
            transaction.setCreatedDate(Instant.now());
            transactionCreated = transactionRepository.save(transaction);

        }catch(Exception e) {
            log.error("Cannot save the transaction: {} , error : {}", transaction, e);
        }

        return transactionCreated;
    }



    @Override
    public Transaction updateTransaction(Transaction modifiedTransaction) {

        log.debug("Update a Transaction: {}", modifiedTransaction);

        Transaction updatedTransaction = null;
        Transaction findedTransaction = transactionDAO.findById(modifiedTransaction.getId());

        if (findedTransaction != null) {

            try{
                updatedTransaction = transactionRepository.save(modifiedTransaction);
            }catch(Exception e){
                log.error("Cannot save Transaction: {} , error : {}", modifiedTransaction, e);
            }
        }else{
            log.warn("Cannot save Transaction: {}, because it doesn´t exist", updatedTransaction);
        }
        return updatedTransaction;
    }


    @Override
    public List<Transaction> findAll() {
        log.info("REST request to find all Transactions");

        return this.transactionRepository.findAll();
    }

    @Override
    public Transaction findOne(Long id) {
        log.info("REST request to find one BankAccount by id");

        if (id == null)
            return null;
        return this.transactionDAO.findById(id);
    }


    @Override
    public void deleteTransaction(Transaction transactionToDelete){
        log.info("REST request to delete an Transaction by id");
        this.transactionRepository.deleteById(transactionToDelete.getId());

    }
}
