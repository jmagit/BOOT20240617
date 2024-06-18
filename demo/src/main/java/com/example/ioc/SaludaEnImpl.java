package com.example.ioc;

import org.springframework.stereotype.Component;

//@Component
public class SaludaEnImpl implements Saluda {
	Entorno entorno;
	
	public SaludaEnImpl(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public void saluda(String nombre) {
		entorno.write("Hello " + nombre);
	}
}
