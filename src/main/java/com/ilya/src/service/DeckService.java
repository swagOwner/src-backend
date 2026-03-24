package com.ilya.src.service;

import com.ilya.src.dto.DeckCreateRequest;
import com.ilya.src.dto.DeckUpdateRequest;
import com.ilya.src.entity.Deck;
import com.ilya.src.exception.ResourceNotFoundException;
import com.ilya.src.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckRepository deckRepository;

    public List<Deck> getAll(){
        return deckRepository.findAll();
    }

    public Deck getById(Long id){
        return deckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deck with id " + id + " not found"));
    }

    public Deck create(DeckCreateRequest request) {
        Deck deck = new Deck();
        deck.setName(request.getName());
        deck.setDescription(request.getDescription());
        return deckRepository.save(deck);
    }

    public Deck update(Long id, DeckUpdateRequest request){
        Deck deck = getById(id);
        deck.setName(request.getName());
        deck.setDescription(request.getDescription());
        return deckRepository.save(deck);
    }

    public void delete(Long id){
        Deck deck = getById(id);
        deckRepository.delete(deck);
    }
}
