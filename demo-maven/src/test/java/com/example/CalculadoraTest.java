package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase Calculadora")
class CalculadoraTest {
	Calculadora calculadora;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculadora = new Calculadora();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("Metodo Add")
	class Add {
		@Nested
		class OK {
			@Test
			@DisplayName("Suma dos enteros")
			@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
			void testAdd() {
//				var calculadora = new Calculadora();
				assertEquals(3, calculadora.add(1, 2));
//				assertEquals(3, calculadora.add(4, -1), "caso 4, -1");
//				assertEquals(3, calculadora.add(-1, -1), "caso -1, -1");
//				assertEquals(3, calculadora.add(0, 0), "caso 0,0");
			}

			@Test
			@DisplayName("Suma IEEE7..")
			void testAdd2() {
//				var calculadora = new Calculadora();

				var result = calculadora.add(0.1, 0.2);

				assertEquals(0.3, result);
			}

		}

		@Nested
		class KO {

		}
	}

	@Nested
	@DisplayName("Metodo Div")
	class Div {
		Calculadora calculadora;
		@BeforeEach
		void setUp() throws Exception {
			calculadora = new Calculadora();
		}

		@Nested
		class OK {
			@Test
			@DisplayName("Divide dos enteros")
			void testDivInt() {
//				var calculadora = new Calculadora();

				var result = calculadora.div(3, 2);

				assertEquals(1, result);
			}

			@Test
			@DisplayName("Divide dos reales")
			void testDivRealOK() {
//				var calculadora = new Calculadora();

				var result = calculadora.div(3.0, 2.0);

				assertEquals(1.5, result);
			}

		}

		@Nested
		class KO {
			@Test
			@DisplayName("Division por 0")
			void testDivRealKO() {
//				var calculadora = new Calculadora();

				assertThrows(ArithmeticException.class, () -> calculadora.div(3.0, 0));
			}

		}
	}

}
