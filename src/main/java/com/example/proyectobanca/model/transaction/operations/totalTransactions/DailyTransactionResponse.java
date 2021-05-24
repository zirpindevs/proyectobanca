package com.example.proyectobanca.model.transaction.operations.totalTransactions;

import java.util.ArrayList;
import java.util.List;

public class DailyTransactionResponse {

    private String status;

    private String startDate;

    private String endDate;

    private List<DailyTransactionRange> dailyTransactionRanges = new ArrayList<>();

    public DailyTransactionResponse() {
    }

    public DailyTransactionResponse(String status) {
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

    public List<DailyTransactionRange> getDailyTransactionRanges() {
        return dailyTransactionRanges;
    }

    public void setDailyTransactionRanges(List<DailyTransactionRange> dailyTransactionRanges) {
        this.dailyTransactionRanges = dailyTransactionRanges;
    }

    @Override
    public String toString() {
        return "DailyTransactionResponse{" +
                "status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", dailyTransactionRanges=" + dailyTransactionRanges +
                '}';
    }
}
