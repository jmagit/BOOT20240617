package com.example.domains.contracts.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.core.test.Lentos;
import com.example.domains.entities.Actor;

@DataJpaTest
@Lentos
class ActorRepositoryMemoryTest {
	@Autowired
	private TestEntityManager em;

	@Autowired
	ActorRepository dao;

	@BeforeEach
	void setUp() throws Exception {
		var item = new Actor(0, "Pepito", "GRILLO");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Carmelo", "COTON");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Capitan", "TAN");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
	}
	
	@Test
	void testGetAll_isNotEmpty() {
		var rslt = dao.findAll();
		assertThat(rslt.size()).isEqualTo(3);
		assertThat(dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("C").size()).isEqualTo(1);
		assertThat(dao.findByJPQL(1).size()).isEqualTo(3);
	}

	@Test
	void testGetOne() {
		var item = dao.findById(dao.findAll().get(0).getActorId());
		assertThat(item.isPresent()).isTrue();
		assertEquals("Pepito", item.get().getFirstName());
	}

	
	@Test
	void findBySQLTest() {
		assertThat(dao.findBySQL(1).size()).isGreaterThan(0);
	}

}
