package com.application.cursova.controller;

import com.application.cursova.model.Account;
import com.application.cursova.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        System.out.println("Всі рахунки були виведені.");
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        System.out.println("Рахунок з ID " + id + " був виведений.");
        return accountService.getAccountById(id);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        System.out.println("Новий рахунок був створений.");
        try {
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity.ok(createdAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long id) {
        try {
            Account deletedAccount = accountService.deleteAccount(id);
            return ResponseEntity.ok(deletedAccount);
        } catch (Exception e) {
            System.err.println("Помилка при видаленні рахунку з ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/balance/{balance}")
    public List<Account> getAccountsByBalance(@PathVariable BigDecimal balance) {
        System.out.println("Рахунки з балансом більше ніж " + balance + " були виведені.");
        return accountService.getAccountsByBalance(balance);
    }

    @GetMapping("/owner/{owner}")
    public List<Account> getAccountsByOwner(@PathVariable String owner) {
        System.out.println("Рахунки з власником " + owner + " були виведені.");
        return accountService.getAccountsByOwner(owner);
    }
}