package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.BankAccountDAO;
import com.example.proyectobanca.dao.impl.BankAccountDAOImpl;
import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final Logger log = LoggerFactory.getLogger(BankAccountDAOImpl.class);

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountDAO bankAccountDAO;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountDAO bankAccountDAO) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountDAO = bankAccountDAO;
    }


    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        log.debug("Create BankAccount: {}", bankAccount);

        BankAccount bankAccountCreated = null;

        try {
            bankAccount.setCreatedDate(Instant.now());
            bankAccount.setLastModified(Instant.now());
            bankAccountCreated = bankAccountRepository.save(bankAccount);
        } catch (Exception e) {
            log.error("Cannot save the bankAccount: {} , error : {}", bankAccount, e);
        }

        return bankAccountCreated;
    }


    @Override
    public BankAccount updateBankAccount(BankAccount modifiedBankAccount) {

        log.debug("Update a BankAccount: {}", modifiedBankAccount);

        BankAccount updatedBankAccount = null;
        BankAccount findedBankAccount = bankAccountDAO.findById(modifiedBankAccount.getId());

        if (findedBankAccount != null) {

            try {
                modifiedBankAccount.setLastModified(Instant.now());
                updatedBankAccount = bankAccountRepository.save(modifiedBankAccount);
            } catch (Exception e) {
                log.error("Cannot save BankAccount: {} , error : {}", modifiedBankAccount, e);
            }
        } else {
            log.warn("Cannot save BankAccount: {}, because it doesn´t exist", updatedBankAccount);
        }
        return updatedBankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        log.info("REST request to find all BankAccounts");

        return this.bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findOne(Long id) {
        log.info("REST request to find one BankAccount by id");

        if (id == null)
            return null;
        return this.bankAccountDAO.findById(id);
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccountToDelete) {
        log.info("REST request to BankAccount an BankAccount by id");
        this.bankAccountRepository.deleteById(bankAccountToDelete.getId());
    }

}
