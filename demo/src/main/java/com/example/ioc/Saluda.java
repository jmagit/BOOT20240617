package com.example.ioc;

import jakarta.annotation.Nonnull;

public interface Saluda {

	void saluda(@Nonnull String nombre);
	int getContador();
}