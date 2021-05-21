package com.example.proyectobanca.model.transaction.operations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

public class DailyBalanceRange {

    private String startDate;

    private String endDate;

    private Timestamp date;

    private Double balance;

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
}
