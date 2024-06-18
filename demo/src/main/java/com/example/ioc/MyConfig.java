package com.example.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {
	@Bean
	int contInit() { return 0; }
	
	@Bean
	@Scope("prototype")
	Entorno entorno(int contInit) {
		return new EntornoImpl(contInit);
	}
}
