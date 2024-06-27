package com.example.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domains.entities.Category;

@SpringBootTest
class CategoryRepositoryTest {
	@Autowired
	CategoryRepository dao;
	
	@Test
	void consultaSuperficialTest() {
		try {
			dao.findAll();
		} catch (Exception ex) {
			fail("Fallo en el mapeo JPA: " + ex.getMessage());
		}
	}
	@Test
	void modificacionSuperficialTest() {
		try {
			var item = dao.findAll().getFirst();
			var aux = item.getName();
			item.setName("kk");
			dao.save(item);
			item.setName(aux);
			dao.save(item);
		} catch (Exception ex) {
			fail("Fallo en actualización: " + ex.getMessage());
		}
	}
	@Test
	void createDeleteTest() {
		try {
			var item = dao.save(new Category(0, "kk"));
			dao.delete(item);
		} catch (Exception ex) {
			fail("Fallo en actualización: " + ex.getMessage());
		}
	}

}
