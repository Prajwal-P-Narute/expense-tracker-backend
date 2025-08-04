package com.my.expense_tracker_ui.controller;

import com.my.expense_tracker_ui.dto.TransactionRequest;
import com.my.expense_tracker_ui.dto.TransactionResponse;
import com.my.expense_tracker_ui.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Value("${expense.opening-balance}")
    private double openingBalance;

    @Autowired
    private TransactionService service;

    @PostMapping
    public TransactionResponse addTransaction(@RequestBody TransactionRequest request) {
        return service.addTransaction(request);
    }

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/opening-balance")
    public double getOpeningBalance() {
        return openingBalance;
    }
}
