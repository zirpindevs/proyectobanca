package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.BankAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    private final Logger log = LoggerFactory.getLogger(BankAccount.class);

    private final BankAccountService bankAccountService;

    private final BankAccountRepository bankAccountRepository;

    public BankAccountController(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository) {
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * FIND ALL BANKACCOUNTS
     * @return List<BankAccount>
     */
    @ApiOperation("Get all BankAccounts")
    @RequestMapping(method = RequestMethod.GET, value = "/bankaccounts")
    public List<BankAccount> findAllBankAccount(){
        log.debug("REST request to find all BankAccounts");

        return this.bankAccountRepository.findAll();
    }

    /**
     * FIND BANKACCOUNTS BY ID
     *
     * @param id
     * @return ResponseEntity<BankAccount>
     * @throws URISyntaxException
     */
    @ApiOperation("Get BankAccount by id")
    @GetMapping("/bankaccounts/{id}")
    public ResponseEntity<BankAccount> findBankAccountById(@ApiParam("Primary key of bankaccounts: Long") @PathVariable Long id) throws URISyntaxException {
        BankAccount findBankAccount = this.bankAccountService.findOne(id);

        if (findBankAccount == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findBankAccount);

    }

    /**
     * CREATE BANKACCOUNTS
     *
     * @return ResponseEntity<BankAccount>
     * @throws URISyntaxException
     */
    @PostMapping("/bankaccounts")
    @ApiOperation("Create BankAccount")
    public ResponseEntity<BankAccount> createBankAccount(
            @ApiParam("BankAccounts that you want to create: BankAccounts")
            @RequestBody BankAccount bankAccountToCreate) throws URISyntaxException {
        log.debug("REST request to create new a BankAccount: {} ", bankAccountToCreate);

        if (bankAccountToCreate.getNumAccount() == null || bankAccountToCreate.getEnabled() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankAccount checkbankAccount = this.bankAccountRepository.findBynumAccount(bankAccountToCreate.getNumAccount());

        if(checkbankAccount == null) {
            BankAccount createBankAccount = this.bankAccountService.createBankAccount(bankAccountToCreate);

            return ResponseEntity
                    .created(new URI("/api/etiquetas/" + bankAccountToCreate.getNumAccount()))
                    .body(bankAccountToCreate);
        }
        else
        {
            log.warn("already in use");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * UPDATE BANKACCOUNTS
     *
     * @param modifiedBankAccount
     * @return ResponseEntity<BankAccount>
     */
    @PutMapping("/bankaccounts")
    @ApiOperation("Update enable property BankAccount in DB")
    public ResponseEntity<BankAccount> updateBankAccount(
            @ApiParam("BankAccount that you want to update enable status: BankAccount") @RequestBody BankAccount modifiedBankAccount) {

        log.debug("REST request to update enable status of BankAccount: {} ", modifiedBankAccount);

        if(modifiedBankAccount.getId() == null && modifiedBankAccount.getNumAccount() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankAccount result = bankAccountService.updateBankAccount(modifiedBankAccount);

        if (result.getId() == -404L)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(result);

    }


    /**
     * DELETE BANKACCOUNTS
     * @param id
     * @return
     */
    @DeleteMapping("/bankaccounts/{id}")
    @ApiOperation("Delete bankaccount of DB by Id")
    public ResponseEntity<Void> deleteBankAccount(@ApiParam("Primary key of bankaccount: Long") @PathVariable Long id){
        log.debug("REST request to delete a BankAccount: {} ", id);

        BankAccount bankAccountToDelete = this.bankAccountService.findOne(id);

        if (bankAccountToDelete.getId() == null) {
            log.warn("BankAccount not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.bankAccountService.deleteBankAccount(bankAccountToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
