package com.my.expense_tracker_ui.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;
    private LocalDate date;
    private String type; // debit or credit
    private String category;
    private double amount;
    private String reimbursable; // Y or N
    private String comments;
    private String userEmail;

    @Transient
    private double runningBalance; // Add this

}
