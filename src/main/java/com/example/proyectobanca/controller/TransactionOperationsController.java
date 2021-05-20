package com.example.proyectobanca.controller;

import com.example.proyectobanca.model.Transaction;
import com.example.proyectobanca.model.transaction.operations.DailyBalance;
import com.example.proyectobanca.service.TransactionOperationsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionOperationsController {

    private final Logger log = LoggerFactory.getLogger(Transaction.class);

    private final TransactionOperationsService transactionOperationsService;

    public TransactionOperationsController(TransactionOperationsService transactionOperationsService) {
        this.transactionOperationsService = transactionOperationsService;
    }


    /**
     * Get the current balance per day between two dates for a bank account. Also, it returns the same balance from the
     * same date range, but from the previous year.
     * @return List of DailyBalance per day from database
     */
    @GetMapping("/transactions-operations/daily-balance-bankaccount/{id}")
    @ApiOperation("Get balance per day between two dates")
    public ResponseEntity<List<DailyBalance>> getDailyBalanceByDateRangeByNumAccount(
            @ApiParam("Primary key of Bank account: Long") @PathVariable Long id,
            @ApiParam("Start date: LocalDate") @QueryParam("startDate") String startDate,
            @ApiParam("End date: LocalDate") @QueryParam("endDate") String endDate,
            @ApiParam("Pagination: page from which the records start to be displayed (optional): Integer") @QueryParam("page") String page,
            @ApiParam("Pagination: number of records displayed per page (optional): Integer") @QueryParam("limit") String limit
    ) {

        Map<String, String> map1 = new HashMap<>();
        map1.put("startDate", startDate + " 00:00:00.000000");
        map1.put("endDate", endDate + " 23:59:59.999999");
        map1.put("page", page);
        map1.put("limit", limit);

        List<DailyBalance> result = transactionOperationsService.getDailyBalanceByDateRangeByNumAccount(map1);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

/*        if (result.get(0).getId()== -500L)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);*/

        return ResponseEntity.ok().body(result);
    }
}
