package com.my.expense_tracker_ui.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private LocalDate date;
    private String type;
    private String category;
    private double amount;
    private String reimbursable;
    private String comments;
    private double runningBalance; // ✅ Add this line
}
