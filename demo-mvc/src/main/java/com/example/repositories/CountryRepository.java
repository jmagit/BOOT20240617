package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.model.Country;

@Repository
@RepositoryRestResource(path="paises")
public interface CountryRepository extends JpaRepository<Country, Long> {

}
