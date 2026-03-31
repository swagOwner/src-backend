package com.ilya.src.controller;

import com.ilya.src.dto.CardCreateRequest;
import com.ilya.src.entity.Card;
import com.ilya.src.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ilya.src.dto.ReviewRequest;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<Card> getAll(){
        return cardService.getAll();
    }

    @GetMapping("/{id}")
    public Card getById(@PathVariable Long id) {
        return cardService.getById(id);
    }

    @PostMapping
    public Card create(@Valid @RequestBody CardCreateRequest request) {
        return cardService.create(request);
    }

    @PostMapping("/{id}/review")
    public Card review(@PathVariable Long id, @Valid @RequestBody ReviewRequest request){
        return cardService.review(id, request.quality());
    }

    @GetMapping("/due")
    public List<Card> getDueCards() {
        return cardService.getDueCards();
    }

}
