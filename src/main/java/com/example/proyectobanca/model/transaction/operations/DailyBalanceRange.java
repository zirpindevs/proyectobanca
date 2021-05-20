package com.example.proyectobanca.model.transaction.operations;

import java.time.LocalDate;
import java.util.List;

public class DailyBalanceRange {

    private String startDate;

    private LocalDate endDate;

    private List<DailyBalance> dailyBalances;
}
