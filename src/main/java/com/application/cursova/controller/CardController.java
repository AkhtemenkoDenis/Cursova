package com.application.cursova.controller;

import com.application.cursova.model.Card;
import com.application.cursova.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        System.out.println("Всі карти були виведена.");
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        System.out.println("Карта з ID " + id + " була виведена.");
        return cardService.getCardById(id);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        try {
            Card createdCard = cardService.createCard(card);
            System.out.println("Нова карта була створена.");
            return ResponseEntity.ok(createdCard);
        } catch (Exception e) {
            System.err.println("Помилка при створенні карти: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
        try {
            Card updatedCard = cardService.updateCard(id, card);
            System.out.println("Карта з ID " + id + " була оновлена.");
            return ResponseEntity.ok(updatedCard);
        } catch (Exception e) {
            System.err.println("Помилка при оновленні карти: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable Long id) {
        try {
            Card deletedCard = cardService.deleteCard(id);
            System.out.println("Карта з ID " + id + " була видалена.");
            return ResponseEntity.ok(deletedCard);
        } catch (Exception e) {
            System.err.println("Помилка при видаленні карти: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/account/{accountId}")
    public List<Card> getCardsByAccountId(@PathVariable Long accountId) {
        System.out.println("Карти з ID рахунку " + accountId + " були виведені.");
        return cardService.getCardsByAccountId(accountId);
    }

    @GetMapping("/type/{type}")
    public List<Card> getCardsByType(@PathVariable String type) {
        System.out.println("Карти з типом " + type + " були виведені.");
        return cardService.getCardsByType(type);
    }
}