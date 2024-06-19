package com.example;

import java.util.Optional;

public class Persona {
	private int id;
	private String nombre;
	private String apellidos;
	
	public Persona(int id, String nombre) {
		this.id = id;
		setNombre(nombre);
	}
	
	public Persona(int id, String nombre, String apellidos) {
		this.id = id;
		setNombre(nombre);
		setApellidos(apellidos);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String valor) {
		if(valor == null || "".equals(valor.trim()))
			throw new IllegalArgumentException("Falta el nombre");
		this.nombre = valor;
	}
//	public boolean hasApellidos() {
//		return apellidos != null;
//	}
//	public String getApellidos() {
//		if(apellidos == null) 
//			throw new NullPointerException("No tengo apellidos");
//		return apellidos;
//	}
	public Optional<String> getApellidos() {
		return Optional.ofNullable(apellidos);
	}
	public void setApellidos(String valor) {
		if(valor == null)
			throw new IllegalArgumentException("Falta los apellidos");
		this.apellidos = valor;
	}
	public void clearApellidos() {
		this.apellidos = null;
	}
	
	
	
}
