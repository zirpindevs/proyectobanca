package com.example.proyectobanca.model.transaction.operations;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DailyBalance {

    private String startDate;

    private LocalDate endDate;

    private Timestamp date;

    private Double balance;

    public DailyBalance() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Timestamp getDate() { return date; }

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
