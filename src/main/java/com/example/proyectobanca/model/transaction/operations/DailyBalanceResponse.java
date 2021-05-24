package com.example.proyectobanca.model.transaction.operations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DailyBalanceResponse {

    private String status;

    private String startDate;

    private String endDate;

    private String numBankAccount;

    private List<DailyBalanceRange> dailyBalanceRanges = new ArrayList<>();

    public DailyBalanceResponse() {
    }

    public DailyBalanceResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getNumBankAccount() {
        return numBankAccount;
    }

    public void setNumBankAccount(String numBankAccount) {
        this.numBankAccount = numBankAccount;
    }

    public List<DailyBalanceRange> getDailyBalanceRanges() {
        return dailyBalanceRanges;
    }

    public void setDailyBalanceRanges(List<DailyBalanceRange> dailyBalanceRanges) {
        this.dailyBalanceRanges = dailyBalanceRanges;
    }

    @Override
    public String toString() {
        return "DailyBalanceResponse{" +
                "status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numBankAccount='" + numBankAccount + '\'' +
                ", dailyBalanceRanges=" + dailyBalanceRanges +
                '}';
    }
}
