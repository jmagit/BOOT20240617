package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActorRepositoryRealTest {
	@Autowired
	ActorRepository dao;
	
	@Test
	void test() {
		assertThat(dao.findAll().size()).isGreaterThan(200);
	}
	
	@Test
	void findTop5ByLastNameStartingWithOrderByFirstNameDescTest() {
		assertThat(dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("P").size()).isEqualTo(5);
	}
	
	@Test
	void findBySQLTest() {
		assertThat(dao.findBySQL(1).size()).isGreaterThan(0);
	}

}
