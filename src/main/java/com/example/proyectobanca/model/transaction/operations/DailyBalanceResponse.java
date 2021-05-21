package com.example.proyectobanca.model.transaction.operations;

import java.util.ArrayList;
import java.util.List;

public class DailyBalanceResponse {

    private String status;

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
                ", dailyBalanceRanges=" + dailyBalanceRanges +
                '}';
    }
}
