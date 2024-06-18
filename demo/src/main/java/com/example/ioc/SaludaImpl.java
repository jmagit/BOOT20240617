package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
	public void saluda(String nombre) {
		entorno.write("Hola " + nombre);
	}

	@Override
	public int getContador() {
		return entorno.getContador();
	}

}
