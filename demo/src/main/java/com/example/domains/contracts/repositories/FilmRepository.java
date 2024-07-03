package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.domains.core.contracts.repositories.ProjectionsAndSpecificationJpaRepository;
import com.example.domains.entities.Film;

@RepositoryRestResource(path="peliculas", itemResourceRel="pelicula", collectionResourceRel="peliculas")
public interface FilmRepository extends ProjectionsAndSpecificationJpaRepository<Film, Integer> {
	@RestResource(path = "por-titulo")
	List<Film> findByTitleStartingWith(String nombre);
}
