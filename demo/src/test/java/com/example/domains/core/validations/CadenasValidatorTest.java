package com.example.domains.core.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.core.test.SpaceCamelCase;
import com.example.domains.core.entities.EntityBase;

import lombok.Value;

class CadenasValidatorTest {

	@Nested
	@DisplayName("Pruebas del método IsNIF")
	@DisplayNameGeneration(SpaceCamelCase.class)
	class IsNIF {

		@ParameterizedTest(name = "Caso: {0}")
		@ValueSource(strings = { "12345678z", "12345678Z", "4g" })
		void casosValidos(String caso) {
			assertTrue(CadenasValidator.isNIF(caso));
		}

		@ParameterizedTest(name = "Caso: {0}")
		@ValueSource(strings = { "12345678", "Z12345678", "kk", "0T" })
		@NullAndEmptySource
		void casosInvalidos(String caso) {
			assertFalse(CadenasValidator.isNIF(caso));
		}

		@Test
		void testIsNotNIF() {
			assertTrue(CadenasValidator.isNotNIF(""));
		}
	}
	@Nested
	@DisplayName("Pruebas de la anotación @NIF")
	@DisplayNameGeneration(SpaceCamelCase.class)
	class Anotacion {
		@Value
		class Dummy extends EntityBase<Dummy> {
			@NIF
			String nif;			
		}
		@ParameterizedTest(name = "Caso: {0}")
		@ValueSource(strings = { "12345678z" })
		@NullSource
		void casosValidos(String caso) {
			var dummy = new Dummy(caso);
			assertTrue(dummy.isValid());
		}
		@Test
		void casoInvalido() {
			var dummy = new Dummy("0T");
			assertTrue(dummy.isInvalid());
			assertEquals("ERRORES: nif: 0T no es un NIF válido.", dummy.getErrorsMessage());
		}
	}
}
