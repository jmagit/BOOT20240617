package com.example.domains.entities.models;

import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Projection(name = "peli-corta", types = {Film.class})
@Schema(name = "Pelicula (Corto)", description = "Version corta de las peliculas")
public interface FilmShortDTO {
	@JsonProperty("id")
	int getFilmId();
	@JsonProperty("titulo")
	String getTitle();
}
