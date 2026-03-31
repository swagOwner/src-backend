package com.ilya.src.service;

import com.ilya.src.dto.CardCreateRequest;
import com.ilya.src.entity.Card;
import com.ilya.src.entity.Deck;
import com.ilya.src.exception.ResourceNotFoundException;
import com.ilya.src.repository.CardRepository;
import com.ilya.src.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card getById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + id + " not found"));
    }

    public Card create(CardCreateRequest request) {
        Deck deck = deckRepository.findById(request.deckId())
                .orElseThrow(() -> new ResourceNotFoundException("Deck with id " + request.deckId() + " not found"));

        Card card = new Card();
        card.setFront(request.front());
        card.setBack(request.back());
        card.setDeck(deck);
        card.setDueAt(LocalDateTime.now());
        card.setIntervalDays(0);
        card.setRepetitions(0);
        card.setEaseFactor(2.5);

        return cardRepository.save(card);
    }

    public Card review(Long id, int quality){
        Card card = getById(id);

        if(quality < 3){
            card.setRepetitions(0);
            card.setIntervalDays(1);
        } else {
            int repetitions = card.getRepetitions() + 1;
            card.setRepetitions(repetitions);

            if (repetitions == 1){
                card.setIntervalDays(1);
            } else if (repetitions == 2){
                card.setIntervalDays(6);
            } else {
                int nextInterval = (int) Math.round(card.getIntervalDays() *
                        card.getEaseFactor());
                card.setIntervalDays(Math.max(nextInterval, 1));
            }
        }

        double ef = card.getEaseFactor() +
                (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));

        if (ef < 1.3){
            ef = 1.3;
        }

        card.setEaseFactor(ef);
        card.setDueAt(LocalDateTime.now().plusDays(card.getIntervalDays()));

        return cardRepository.save(card);
    }

    public List<Card> getByDeckId(Long deckId) {
        return cardRepository.findByDeckId(deckId);
    }

    public List<Card> getDueCards() {
        return cardRepository.findByDueAtLessThanEqual(LocalDateTime.now());
    }

    public List<Card> search(String query) {
        if (query == null || query.isBlank()) {
            return cardRepository.findAll();
        }
        return cardRepository.findByFrontContainingIgnoreCaseOrBackContainingIgnoreCase(query, query);
    }
}

