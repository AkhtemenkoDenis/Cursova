package com.application.cursova.repository;

import com.application.cursova.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner(String owner);
    List<Account> findByBalanceGreaterThan(BigDecimal balance);
}