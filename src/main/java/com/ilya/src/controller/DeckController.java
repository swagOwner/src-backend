package com.ilya.src.controller;


import com.ilya.src.dto.DeckCreateRequest;
import com.ilya.src.dto.DeckUpdateRequest;
import com.ilya.src.entity.Card;
import com.ilya.src.entity.Deck;
import com.ilya.src.repository.DeckRepository;
import com.ilya.src.service.CardService;
import com.ilya.src.service.DeckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;
    private final CardService cardService;

    @GetMapping
    public List<Deck> getAll(){
        return deckService.getAll();
    }

    @GetMapping("/{id}")
    public Deck getById(@PathVariable Long id){
        return deckService.getById(id);
    }

    @PostMapping
    public Deck create(@Valid @RequestBody DeckCreateRequest request) {
        return deckService.create(request);
    }

    @PutMapping("/{id}")
    public Deck update(@PathVariable Long id, @Valid @RequestBody DeckUpdateRequest request){
        return deckService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deckService.delete(id);
    }

    @GetMapping("/{id}/cards")
    public List<Card> getCardsByDeck(@PathVariable Long id) {
        return cardService.getByDeckId(id);
    }
}
