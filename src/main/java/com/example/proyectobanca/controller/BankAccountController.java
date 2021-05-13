package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.BankAccount;
import com.example.proyectobanca.repository.BankAccountRepository;
import com.example.proyectobanca.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

/* ENDPOINTS

    Cuentas:
            •	Get all
•	Get one by ID
•	Get by Filters:
    o	Get all by User
    o	Get Ingresos/gastos by Fecha
•	Create Cuenta
•	Update by ID
•	Delete by ID

*/

    private final Logger log = LoggerFactory.getLogger(BankAccount.class);

    private final BankAccountService bankAccountService;

    private final BankAccountRepository bankAccountRepository;

    public BankAccountController(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository) {
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }


    /**
     * CREATE BANKACCOUNTS
     *
     * @return ResponseEntity<BankAccount>
     * @throws URISyntaxException
     */
    @PostMapping("/bankaccounts")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccountToCreate) throws URISyntaxException {
        log.debug("REST request to create new a creditcard: {} ", bankAccountToCreate);

        System.out.println(bankAccountToCreate);

        if (bankAccountToCreate.getNumeroCuenta() == null || bankAccountToCreate.getUsers() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        BankAccount checkbankAccount = this.bankAccountRepository.findBynumeroCuenta(bankAccountToCreate.getNumeroCuenta());


        if(checkbankAccount == null) {
            BankAccount createBankAccount = this.bankAccountService.createBankAccount(bankAccountToCreate);

            return ResponseEntity
                    .created(new URI("/api/etiquetas/" + bankAccountToCreate.getNumeroCuenta()))
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
    public ResponseEntity<BankAccount> updateCreditCard(@RequestBody BankAccount modifiedBankAccount) {
        log.debug("REST request to update one BankAccount: {} ", modifiedBankAccount);


        if (modifiedBankAccount.getId() == null) {
            log.warn("update creditcard without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BankAccount updateBankAccount = this.bankAccountService.updateBankAccount(modifiedBankAccount);

        if(updateBankAccount == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(updateBankAccount);

    }

    /**
     * FIND ALL BANKACCOUNTS
     * @return List<CreditCard>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/creditcards")
    public List<BankAccount> findAllCreditCards(){
        log.debug("REST request to find all creditcards");

        return this.bankAccountRepository.findAll();
    }

    /**
     * FIND BANKACCOUNTS BY ID
     *
     * @param id
     * @return ResponseEntity<CreditCard>
     * @throws URISyntaxException
     */
/*    @GetMapping("/creditcards/{id}")
    public ResponseEntity<CreditCard> findCreditCardById(@PathVariable Long id) throws URISyntaxException {
        CreditCard findCreditCard = this.creditCardService.findOne(id);

        if (findCreditCard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findCreditCard);

    }*/

    /**
     * DELETE CREDIT CARD
     * @param id
     * @return
     */
 /*   @DeleteMapping("/creditcards/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id){
        log.debug("REST request to delete a creditcard: {} ", id);

        CreditCard creditCardtToDelete = this.creditCardService.findOne(id);

        if (creditCardtToDelete.getId() == null) {
            log.warn("creditcard not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.creditCardService.deleteCreditCard(creditCardtToDelete);
        return ResponseEntity.noContent().build();
    }*/

}
