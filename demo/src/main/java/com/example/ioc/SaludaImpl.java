package com.example.ioc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.NonNull;

@Component("saludaEs")
//@Qualifier("es")
@Scope("prototype")
@Profile("es")
public class SaludaImpl implements Saluda {
	Entorno entorno;
	
	public SaludaImpl(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public void saluda(@NonNull String nombre) {
//		if(nombre == null)
//			throw new IllegalArgumentException("El nombre es obligatorio.");
		entorno.write("Hola " + nombre.toUpperCase());
	}
	public void saluda() {
		entorno.write("Hola");
	}

	@Override
	public int getContador() {
		return entorno.getContador();
	}

	
	public Optional<Entorno> getEntorno() {
		return Optional.ofNullable(entorno);
	}

}
