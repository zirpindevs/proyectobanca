package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.Transaction;
import com.example.proyectobanca.repository.TransactionRepository;
import com.example.proyectobanca.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final Logger log = LoggerFactory.getLogger(Transaction.class);

    private final TransactionService transactionService;

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }


    /**
     * FIND ALL TRANSACTIONS
     * @return List<Transaction>
     */
    @RequestMapping(method = RequestMethod.GET, value = "/transactions")
    @ApiOperation("Get all Transactions")
    public List<Transaction> findAllTransaction(){
        log.debug("REST request to find all Transaction");

        return this.transactionRepository.findAll();
    }

    /**
     * FIND TRANSACTIONS BY ID
     *
     * @param id
     * @return ResponseEntity<Transaction>
     * @throws URISyntaxException
     */
    @GetMapping("/transactions/{id}")
    @ApiOperation("Get Transaction by Id")
    public ResponseEntity<Transaction> findTransactionById(@ApiParam("Primary key of Transaction: Long") @PathVariable Long id) throws URISyntaxException {
        Transaction findTransaction = this.transactionService.findOne(id);

        if (findTransaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findTransaction);

    }


    /**
     * CREATE TRANSACTIONS
     *
     * @return ResponseEntity<Transaction>
     * @throws URISyntaxException
     */
    @PostMapping("/transactions")
    @ApiOperation("Create a new Transaction in DB")
    public ResponseEntity<Transaction> createTransaction(@ApiParam("Transaction that you want to create: Transaction") @RequestBody Transaction transactiontoCreate) throws URISyntaxException {
        log.debug("REST request to create new a Transaction: {} ", transactiontoCreate);

        if (transactiontoCreate.getImporte() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Transaction> checkTransaction = this.transactionRepository.findById(transactiontoCreate.getId());


        if(checkTransaction == null) {
            Transaction createdTransaction = this.transactionService.createTransaction(transactiontoCreate);

            return ResponseEntity
                    .created(new URI("/api/transactions/" + createdTransaction.getBankAccount()))
                    .body(createdTransaction);
        }
        else
        {
            log.warn("already in use");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * UPDATE TRANSACTIONS
     *
     * @param modifiedTransaction
     * @return ResponseEntity<Transaction>
     */
    @PutMapping("/transactions")
    @ApiOperation("Update an existing Transaction in DB")
    public ResponseEntity<Transaction> updateTransaction(@ApiParam("Transaction that you want to update: Transaction")@RequestBody Transaction modifiedTransaction) {
        log.debug("REST request to update one Transaction: {} ", modifiedTransaction);


        if (modifiedTransaction.getId() == null) {
            log.warn("update Transaction without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Transaction updateTransaction = this.transactionService.updateTransaction(modifiedTransaction);

        if(updateTransaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(updateTransaction);

    }


    /**
     * DELETE TRANSACTIONS
     * @param id
     * @return
     */
    @DeleteMapping("/transactions/{id}")
    @ApiOperation("Delete transaction of DB by Id")
    public ResponseEntity<Void> deleteTransaction(@ApiParam("Primary key of transaction: Long") @PathVariable Long id){
        log.debug("REST request to delete a Transaction: {} ", id);

        Transaction transactionToDelete = this.transactionService.findOne(id);

        if (transactionToDelete.getId() == null) {
            log.warn("transaction not exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.transactionService.deleteTransaction(transactionToDelete);
        return ResponseEntity.noContent().build();
    }
}
