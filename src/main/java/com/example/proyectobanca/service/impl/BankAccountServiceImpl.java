package com.example.proyectobanca.service.impl;

import com.example.proyectobanca.dao.BankAccountDAO;
import com.example.proyectobanca.model.*;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final BankAccountRepository repository;

    private final BankAccountDAO bankAccountDAO;

    public BankAccountServiceImpl(BankAccountRepository repository, BankAccountDAO bankAccountDAO) {
        this.repository = repository;
        this.bankAccountDAO = bankAccountDAO;
    }


    @Override
    public List<BankAccount> findAll() {
        log.info("REST request to find all BankAccounts");

        return this.repository.findAll();
    }

    @Override
    public BankAccount findOne(Long id) {
        log.info("REST request to find one BankAccount by id");

        if (id == null)
            return null;
        return this.bankAccountDAO.findById(id);
    }

    /**
     * Create a new bank account in database - Service
     * @param bankAccountDTO to update
     * @return BankAccount created in database
     */
    @Override
    public BankAccount createOne(BankAccountDTO bankAccountDTO) {

        try {

            BankAccount bankAccountValidated = createValidateBankAccount(bankAccountDTO);

            if (bankAccountValidated.getNumAccount() == null)
                return new BankAccount();;

            return repository.save(bankAccountValidated);

        }catch (Exception e){

            log.error(e.getMessage());
            BankAccount bankAccountError = new BankAccount();
            bankAccountError.setId(-500L);

            return bankAccountError;
        }
    }

    /**
     * It update a bank account of database - Service
     * @param bankAccountDTO to update
     * @return BankAccount updated in database
     */
    @Override
    public BankAccount updateOne(Long id, BankAccountDTO bankAccountDTO) {

        try {

            BankAccount bankAccountValidated = updateValidateBankAccount(id, bankAccountDTO);

            if (bankAccountValidated.getNumAccount() == null){
                BankAccount bankAccountError = new BankAccount();
                bankAccountError.setId(-404L);
                return bankAccountError;
            }


            return repository.save(bankAccountValidated);

        }catch (Exception e){

            log.error(e.getMessage());
            BankAccount bankAccountError = new BankAccount();
            bankAccountError.setId(-500L);

            return bankAccountError;
        }
    }

    /**
     * Delete bank account of database by ID. (Update "deleted" field) - Service
     * @param id bank account primary key that you want to delete
     * @return Optional<Boolean>
     */
    @Override
    public Optional<Boolean> deleteOne(Long id){

       /* try {
            if (id != null && repository.existsById(id)) {

                // Delete users - bank accounts relations
                Optional<Boolean> result = userDao.deleteUsersBankAccountsRelation(id);

                if (Objects.equals(result, Optional.of(true))){
                    repository.deleteById(id);
                }

                return Optional.of(true);
            }
            return Optional.of(false);
        }catch (Exception e){
            log.error(e.getMessage());
            return Optional.empty();
        }*/
        return Optional.empty();
    }


    /**
     * Validate a bank account before to save in db
     * @param bankAccountDTO
     * @return BankAccount
     */
    private BankAccount createValidateBankAccount (BankAccountDTO bankAccountDTO){

        BankAccount bankAccountEmpty = new BankAccount();

        // Only is permit to create a bank account if balance is 0, To change the balance is need to create a transaction
        if ( bankAccountDTO.getNumAccount() == null || bankAccountDTO.getBalance() != null ){

            return bankAccountEmpty;

        }else {

            if (bankAccountDTO.getNumAccount().length() != 24)
                return new BankAccount();

            Boolean numBankAccountExist = repository.existsByNumAccount(bankAccountDTO.getNumAccount());
            if (numBankAccountExist)
                return new BankAccount();

            // If pass all validations

            if ( bankAccountDTO.getEnabled() == null)
                bankAccountDTO.setEnabled(true);


            BankAccount bankAccount = new BankAccount();

            bankAccount.setNumAccount(bankAccountDTO.getNumAccount());
            bankAccount.setBalance(0D);
            bankAccount.setEnabled(true);
            bankAccount.setDeleted(false);
            bankAccount.setCreatedAt(LocalDateTime.now());

            return bankAccount;
        }

    }

    /**
     * Validate a bank account before to update in db. Only is possible to update the "enabled" field
     * @param bankAccountDTO
     * @return BankAccount
     */
    private BankAccount updateValidateBankAccount (Long id, BankAccountDTO bankAccountDTO){

        // Exist ?
        Optional<BankAccount> bankAccountDB = this.repository.findById(id);
        if (ObjectUtils.isEmpty(bankAccountDB))
            return new BankAccount();

        // Only is permit to update the field enabled of a bank account
        if ( bankAccountDTO.getEnabled() == null )
            return new BankAccount();


        // Is not possible to modify the number of bank account
        if (!bankAccountDTO.getNumAccount().equals(bankAccountDB.get().getNumAccount()))
            return new BankAccount();

        // Is not possible to modify the number of bank account
        if (!bankAccountDTO.getBalance().equals(bankAccountDB.get().getBalance()))
            return new BankAccount();

        // Is not possible to modify the number of bank account
        if (!bankAccountDTO.getNumAccount().equals(bankAccountDB.get().getNumAccount()))
            return new BankAccount();

        // Is not possible to modify the number of bank account
        if (!bankAccountDTO.getCreatedAt().equals(bankAccountDB.get().getCreatedAt()))
            return new BankAccount();

        // If pass all validations
        bankAccountDB.get().setEnabled(bankAccountDTO.getEnabled());
        bankAccountDB.get().setUpdatedAt(LocalDateTime.now());

        return bankAccountDB.get();
    }
}
