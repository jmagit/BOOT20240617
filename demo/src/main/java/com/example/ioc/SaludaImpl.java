package com.example.ioc;

import org.springframework.stereotype.Component;

@Component
public class SaludaImpl implements Saluda {
	Entorno entorno;
	
	public SaludaImpl(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public void saluda(String nombre) {
		entorno.write("Hola " + nombre);
	}
}
