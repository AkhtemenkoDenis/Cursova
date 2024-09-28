package com.application.cursova.service;


import com.application.cursova.model.Card;
import com.application.cursova.repository.AccountRepository;
import com.application.cursova.model.Account;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found"));

        existingAccount.setOwner(updatedAccount.getOwner());
        existingAccount.setBalance(updatedAccount.getBalance());

        return accountRepository.save(existingAccount);
    }


    public Account deleteAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            accountRepository.deleteById(id);
            System.out.println("Рахунок з ID " + id + " був видалений.");
            return account;
        } else {
            throw new RuntimeException("Account not found with ID " + id);
        }
    }

    public List<Account> getAccountsByOwner(String owner) {
        return accountRepository.findByOwner(owner);
    }

    public List<Account> getAccountsByBalance(BigDecimal balance) {
        return accountRepository.findByBalanceGreaterThan(balance);
    }
}