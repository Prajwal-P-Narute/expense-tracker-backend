package com.my.expense_tracker_ui.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private LocalDate date;
    private String type;
    private String category;
    private double amount;
    private String reimbursable;
    private String comments;
}
