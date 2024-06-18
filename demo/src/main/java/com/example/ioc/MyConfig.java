package com.example.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;

@Configuration
public class MyConfig {
	@Value("${app.contador.init:1}")
	int contadorInit;
	
	@Bean
	int contInit() { return contadorInit; }
	
	@Bean
	@Scope("prototype")
	Entorno entorno(@Value("${app.contador.init:1}") int contInit) {
//	Entorno entorno(int contInit) {
		return new EntornoImpl(contInit);
	}
	
	@EventListener
	void trataEvento(SaludaImpl.SaludaEvent ev) {
		System.err.println("Evento -> " + ev.tipo() + " -> " + ev.detinatario());
	}
}
