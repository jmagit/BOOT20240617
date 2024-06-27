package com.example.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
class FilmRepositoryTest {
	@Autowired
	FilmRepository dao;
	
	@Test
	@Order(1)
//	@Disabled
	void addTest() {
		try {
			var item = new Film(0, "Test", new Language(1), (byte)1, new BigDecimal(1.0), new BigDecimal(10.5));
			assertTrue(item.isValid());
			item.addActor(new Actor(1));
			item.addActor(new Actor(2));
			item.addActor(new Actor(3));
			item.addCategory(1);
			item.addCategory(2);
			dao.save(item);
		} catch (Exception ex) {
			fail("Fallo en pelicula: " + ex.getMessage());
		}
	}
	
	@Test
	@Order(2)
	@Transactional(readOnly = false)
	void modifyTest() {
		try {
			var item = dao.findAll().getLast();
			item.setTitle("KKKKKKK");
			item.removeActor(new Actor(1));
			item.addActor(new Actor(4));
			item.addActor(new Actor(5));
			item.removeCategory(new Category(2));
			item.addCategory(3);
			dao.save(item);
		} catch (Exception ex) {
			fail("Fallo en pelicula: " + ex.getMessage());
		}
	}
	
	@Test
	@Order(3)
	@Disabled
	void deleteTest() {
		try {
			var item = dao.findAll().getLast();
			dao.delete(item);
		} catch (Exception ex) {
			fail("Fallo en pelicula: " + ex.getMessage());
		}
	}

}
