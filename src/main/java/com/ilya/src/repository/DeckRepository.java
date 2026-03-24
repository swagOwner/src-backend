package com.ilya.src.repository;

import com.ilya.src.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long>{
}
