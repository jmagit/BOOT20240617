package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.example.ioc.Saluda;

@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {

	@TestConfiguration 
	static class Contexto {
		@Bean
		Saluda saluda() {
			var simula = mock(Saluda.class);
			when(simula.getContador()).thenReturn(666);
			return simula;
		}
	}
	
	@Autowired
	Saluda saluda;
	
	@Test
	void contextLoads() {
		assertEquals(666, saluda.getContador());
	}

}
