package com.ilya.src.repository;

import com.ilya.src.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByDeckId(Long deckId);

    List<Card> findByDueAtLessThanEqual(LocalDateTime dateTime);


}
