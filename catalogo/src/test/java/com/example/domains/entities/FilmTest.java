package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class FilmTest {

	@Test
	void test() {
		Film film = new Film(1,"Peli", new Language(1), (byte)5,
				new BigDecimal("0.99"), new BigDecimal("20.95"));
		assertEquals("", film.getErrorsMessage());
	}

}
