package com.ilya.src.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CardCreateRequest(
        @NotNull Long deckId,
        @NotBlank @Size(max = 2000) String front,
        @NotBlank @Size(max = 5000) String back
) {
}

