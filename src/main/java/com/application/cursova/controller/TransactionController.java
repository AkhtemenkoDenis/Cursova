package com.application.cursova.controller;

import com.application.cursova.model.Transaction;
import com.application.cursova.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        System.out.println("Всі транзакції були запитані.");
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        System.out.println("Транзакція з ID " + id + " була запитана.");
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        System.out.println("Нова транзакція була створена.");
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        System.out.println("Транзакція з ID " + id + " була оновлена.");
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        if (updatedTransaction != null) {
            return ResponseEntity.ok(updatedTransaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            if (transaction != null) {
                transactionService.deleteTransaction(id);
                System.out.println("Транзакція з ID " + id + " була видалена.");
                return ResponseEntity.ok(transaction);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Помилка при видаленні транзакції: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(@PathVariable Timestamp date) {
        System.out.println("Транзакції з датою " + date + " були запитані.");
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable String type) {
        System.out.println("Транзакції з типом " + type + " були запитані.");
        List<Transaction> transactions = transactionService.getTransactionsByType(type);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Transaction>> getTransactionsByAmount(@PathVariable BigDecimal amount) {
        System.out.println("Транзакції з сумою більше ніж " + amount + " були запитані.");
        List<Transaction> transactions = transactionService.getTransactionsByAmount(amount);
        return ResponseEntity.ok(transactions);
    }
}