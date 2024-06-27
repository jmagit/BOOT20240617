package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Persona {
	private long id;
	private String nombre; 
	private String correo; 
	private String ip;
}
