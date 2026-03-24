package com.ilya.src.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String front;

    @Column(nullable = false, length = 5000)
    private String back;

    @Column(nullable = false)
    private LocalDateTime dueAt;

    @Column(nullable = false)
    private Integer intervalDays;

    @Column(nullable = false)
    private Integer repetitions;

    @Column(nullable = false)
    private Double easeFactor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deck_id", nullable = false)
    @JsonIgnore
    private Deck deck;
}
