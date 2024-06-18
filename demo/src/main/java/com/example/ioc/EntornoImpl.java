package com.example.ioc;

import org.springframework.stereotype.Service;

//@Service
public class EntornoImpl implements Entorno {
	private int contador;
	
	public EntornoImpl(int contInit) {
		this.contador = contInit;
	}

	@Override
	public void write(String cad) {
		contador++;
		System.out.println(cad);
	}

	public int getContador() {
		return contador;
	}
	
}
