package com.example.proyectobanca.model.transaction.operations;

import java.math.BigInteger;
import java.sql.Timestamp;


public class DailyBalanceRange {

    private String startDate;

    private String endDate;

    private Timestamp date;

    private Double balance;

    private BigInteger totalTransactions;

    public DailyBalanceRange() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BigInteger getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(BigInteger totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
}
