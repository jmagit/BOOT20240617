package com.example.domains.entities.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilmShortDTO(@JsonProperty("id") int filmId, @JsonProperty("titulo") String title) { }
