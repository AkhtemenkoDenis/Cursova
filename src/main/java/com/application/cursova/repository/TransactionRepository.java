package com.application.cursova.repository;

import com.application.cursova.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDate(Timestamp date);
    List<Transaction> findByType(String type);
    List<Transaction> findByAmountGreaterThan(BigDecimal amount);
}