package com.example.proyectobanca.model.transaction.operations.totalTransactions;

import java.math.BigInteger;
import java.sql.Timestamp;

public class DailyTransactionRange {

    private String transactionDate;

    private BigInteger totalTransactions;

    public DailyTransactionRange() {
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigInteger getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(BigInteger totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
}
