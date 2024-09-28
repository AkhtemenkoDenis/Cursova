package com.application.cursova.service;

import com.application.cursova.model.Card;
import com.application.cursova.repository.AccountRepository;
import com.application.cursova.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElse(null);
    }

    public Card createCard(Card card) {
        if (accountRepository.existsById(card.getAccount().getId())) {
            try {
                return cardRepository.save(card);
            } catch (Exception e) {
                System.err.println("Помилка при створенні карти: " + e.getMessage());
                throw e;
            }
        } else {
            throw new RuntimeException("Рахунок не був знайдений з номером " + card.getAccount().getId());
        }
    }

    public Card updateCard(Long id, Card card) {
        Optional<Card> existingCard = cardRepository.findById(id);
        if (existingCard.isPresent()) {
            card.setId(id);
            return cardRepository.save(card);
        }
        return null;
    }

    public Card deleteCard(Long id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            cardRepository.deleteById(id);
            return card;
        } else {
            throw new RuntimeException("Банківська карта не була знайдена з номером " + id);
        }
    }

    public List<Card> getCardsByType(String cardType) {
        return cardRepository.findByCardType(cardType);
    }

    public List<Card> getCardsByAccountId(Long accountId) {
        return cardRepository.findByAccountId(accountId);
    }
}