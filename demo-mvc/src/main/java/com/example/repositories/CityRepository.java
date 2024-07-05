package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.model.City;

@Repository
@RepositoryRestResource(path="ciudades")
public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByCityIdLessThanOrderByCityDesc(long max);
}
