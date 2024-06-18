package com.example.ioc;

import org.springframework.stereotype.Service;

@Service
public class Entorno {
	public void write(String cad) {
		System.out.println(cad);
	}
}
