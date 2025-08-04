package com.my.expense_tracker_ui.repository;

import com.my.expense_tracker_ui.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
