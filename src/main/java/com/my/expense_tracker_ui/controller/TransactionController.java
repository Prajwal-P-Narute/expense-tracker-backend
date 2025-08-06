package com.my.expense_tracker_ui.controller;

import com.my.expense_tracker_ui.dto.TransactionRequest;
import com.my.expense_tracker_ui.dto.TransactionResponse;
import com.my.expense_tracker_ui.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionRequest request) {
        System.out.println("add Transaction method");
        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // ✅ extract email from JWT
        TransactionResponse response = service.addTransaction(request, email);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getAllTransactions(userEmail);
    }

    @GetMapping("/opening-balance")
    public double getOpeningBalance() {
        return openingBalance;
    }
}
