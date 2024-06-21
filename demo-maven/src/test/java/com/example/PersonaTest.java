package com.example;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import com.example.core.test.Smoke;

class PersonaTest {

	@Nested
	@DisplayName("Proceso de instanciacion")
	class Create {
		@Nested
		class OK {
			@Test
			@Smoke
			void soloNombre() {
				var persona = new Persona(1, "Pepito");
//				var persona = new Persona(2, "Pepitos", "grillo");
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(1, persona.getId(), "id"),
						() -> assertEquals("Pepito", persona.getNombre(), "nombre"),
						() -> assertTrue(persona.getApellidos().isEmpty(), "apellidos"));
			}

			@ParameterizedTest(name =  "{0} {1}")
			@CsvSource(value = { "1,Pepito", "2,Pepito Grillo", "3,'Grillo, Pepito'" })
			void soloNombre(int id, String nombre) {
				var persona = new Persona(id, nombre);
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(id, persona.getId(), "id"),
						() -> assertEquals(nombre, persona.getNombre(), "nombre"),
						() -> assertTrue(persona.getApellidos().isEmpty(), "apellidos"));
			}
			@ParameterizedTest(name =  "{0} {1}")
			@CsvSource(value = { "1,Pepito", "2,Pepito,Grillo", "3,'Grillo, Pepito'" })
			@Disabled
			void soloNombre(ArgumentsAccessor args) {
				var persona = args.size() == 3 ? 
						new Persona(args.getInteger(0), args.getString(1), args.getString(2)) :
						new Persona(args.getInteger(0), args.getString(1));
				assertNotNull(persona);
				assertAll("Persona", () -> assertEquals(args.getInteger(0), persona.getId(), "id"),
						() -> assertEquals(args.getString(1), persona.getNombre(), "nombre"),
						() -> assertTrue(args.size() == 3 ? persona.getApellidos().isPresent():persona.getApellidos().isEmpty(), "apellidos"));
//				assumeFalse(true, "falta los apellidos");
			}
		}

		@Nested
		class KO {
			@ParameterizedTest(name =  "{0} {1}")
			@CsvSource(value = { "3,","4,''","5,'    '" })
			void soloNombre(int id, String nombre) {
				assertThrows(Exception.class, () -> new Persona(id, nombre));
			}
		}
		
		@Test
		void ponMayusculasService() {
			var persona = new Persona(1, "Pepito"/*,"Grillo"*/);
			var captor = ArgumentCaptor.forClass(Persona.class);
			var dao = mock(PersonaRepository.class);
			when(dao.getOne(anyInt())).thenReturn(Optional.of(persona));
			var srv = new PersonaService(dao);
			
			srv.ponMayusculas(1);

			//assertEquals("PEPITO", persona.getNombre());
			verify(dao).modify(captor.capture());
			assertAll("Persona", () -> assertEquals(1, captor.getValue().getId(), "id"),
					() -> assertEquals("PEPITO", captor.getValue().getNombre(), "nombre"),
					() -> assertEquals("Grillo", captor.getValue().getApellidos().orElseGet(() -> "sin apellidos"), "apellidos"));
		}
		
		@Test
		void ponMayusculasServiceKO() {
			var dao = mock(PersonaRepository.class);
			when(dao.getOne(anyInt())).thenReturn(Optional.empty());
			var srv = new PersonaService(dao);
			assertThrows(IllegalArgumentException.class, () -> srv.ponMayusculas(1));
			
		}
	}

}
