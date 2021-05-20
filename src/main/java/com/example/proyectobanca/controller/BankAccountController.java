package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.model.BankAccountDTO;
import com.example.proyectobanca.model.CreditCard;
import com.example.proyectobanca.model.CreditCardDTO;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.BankAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
     * Create a new bank account in database
     * @param bankAccountDTO BankAccountDTO to create
     * @return bankAccount BankAccount created
     * @throws URISyntaxException
     */
    @PostMapping("/bankaccounts")
    @ApiOperation("Create a new bank account in DB")
    public ResponseEntity<BankAccount> createBankAccount(@ApiParam("bankAccount that you want to create: BankAccountDTO") @RequestBody BankAccountDTO bankAccountDTO) throws URISyntaxException {

        if(ObjectUtils.isEmpty(bankAccountDTO))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankAccount result = bankAccountService.createOne(bankAccountDTO);

        if (result.getId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .created(new URI("/api/bankaccounts/" + result.getId()))
                .body(result);
    }

    /**
     * It update a bank account of database
     * @param bankAccountDTO BankAccountDTO to update
     * @return CreditCard updated in database
     */
    @PutMapping("/bankaccounts/{id}")
    @ApiOperation("Update only enabled field of BankAccount in DB")
    public ResponseEntity<BankAccount> updateCreditCard(
            @ApiParam("id of BankAccount that you want to update: Long") @PathVariable Long id,
            @ApiParam("BankAccount that you want to update: BankAccountDTO") @RequestBody BankAccountDTO bankAccountDTO
    ){

        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankAccount result = bankAccountService.updateOne(id, bankAccountDTO);

        if (result.getId() == -404L)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (result.getId() == -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Delete bank account of database by ID. Really update the "deleted" field
     * @param id bank account primary key that you want to delete
     * @return void
     */
    @DeleteMapping("/bankaccounts/{id}")
    @ApiOperation("Delete bank account of DB by Id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Primary key of bank account: Long") @PathVariable Long id){

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Boolean> result = bankAccountService.deleteOne(id);

        if (Objects.equals(result, Optional.of(false)))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!result.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.noContent().build();
    }

}
