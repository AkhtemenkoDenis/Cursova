package com.application.cursova.service;

import com.application.cursova.model.Account;
import com.application.cursova.model.Transaction;
import com.application.cursova.repository.AccountRepository;
import com.application.cursova.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getAccount() != null && transaction.getAccount().getId() != null) {
            Optional<Account> account = accountRepository.findById(transaction.getAccount().getId());
            account.ifPresent(transaction::setAccount);
        }
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            transaction.setId(id);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByDate(Timestamp date) {
        return transactionRepository.findByDate(date);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type);
    }

    public List<Transaction> getTransactionsByAmount(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }
}