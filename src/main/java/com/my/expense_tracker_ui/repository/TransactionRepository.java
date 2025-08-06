package com.my.expense_tracker_ui.repository;

import com.my.expense_tracker_ui.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserEmailOrderByDateAscIdAsc(String userEmail);
}
