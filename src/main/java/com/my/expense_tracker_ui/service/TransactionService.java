package com.my.expense_tracker_ui.service;

import com.my.expense_tracker_ui.config.ExpenseConfig;
import com.my.expense_tracker_ui.dto.TransactionRequest;
import com.my.expense_tracker_ui.dto.TransactionResponse;
import com.my.expense_tracker_ui.model.Transaction;
import com.my.expense_tracker_ui.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final ExpenseConfig expenseConfig;

    public TransactionService(TransactionRepository repository, ExpenseConfig expenseConfig) {
        this.repository = repository;
        this.expenseConfig = expenseConfig;
    }

    public TransactionResponse addTransaction(TransactionRequest request) {
        List<Transaction> all = repository.findAll();
        double latestBalance = all.isEmpty() ? expenseConfig.getOpeningBalance() : all.get(all.size() - 1).getRunningBalance();

        double newBalance = request.getType().equalsIgnoreCase("credit")
                ? latestBalance + request.getAmount()
                : latestBalance - request.getAmount();

        Transaction transaction = Transaction.builder()
                .date(request.getDate())
                .type(request.getType())
                .category(request.getCategory())
                .amount(request.getAmount())
                .reimbursable(request.getReimbursable())
                .comments(request.getComments())
                .runningBalance(newBalance)
                .build();

        Transaction saved = repository.save(transaction);

        return new TransactionResponse(
                saved.getId(), saved.getDate(), saved.getType(),
                saved.getCategory(), saved.getAmount(), saved.getReimbursable(),
                saved.getComments(), saved.getRunningBalance()
        );
    }

    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());

        double balance = expenseConfig.getOpeningBalance();

        for (Transaction tx : transactions) {
            double amount = tx.getAmount();
            if ("credit".equalsIgnoreCase(tx.getType())) {
                balance += amount;
            } else if ("debit".equalsIgnoreCase(tx.getType())) {
                balance -= amount;
            }
            tx.setRunningBalance(balance);
        }

        return transactions.stream()
                .map(tx -> new TransactionResponse(
                        tx.getId(),
                        tx.getDate(),
                        tx.getType(),
                        tx.getCategory(),
                        tx.getAmount(),
                        tx.getReimbursable(),
                        tx.getComments(),
                        tx.getRunningBalance()  // <- ensure this is set here
                ))
                .collect(Collectors.toList());
    }
}
