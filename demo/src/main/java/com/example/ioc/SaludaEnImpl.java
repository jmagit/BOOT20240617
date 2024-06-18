package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("saludaEn")
//@Qualifier("en")
@Profile("en")
public class SaludaEnImpl implements Saluda {
	Entorno entorno;
	
	public SaludaEnImpl(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public void saluda(String nombre) {
		entorno.write("Hello " + nombre);
	}
	@Override
	public int getContador() {
		return entorno.getContador();
	}
}
