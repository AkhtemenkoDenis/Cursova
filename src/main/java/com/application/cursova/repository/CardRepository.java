package com.application.cursova.repository;

import com.application.cursova.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardType(String cardType);
    List<Card> findByAccountId(Long accountId);
}