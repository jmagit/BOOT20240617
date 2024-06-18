package com.example.ioc;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component("saludaEs")
//@Qualifier("es")
@Scope("prototype")
@Profile("es")
public class SaludaImpl implements Saluda {
//	public static class SaludaEvento {
//		private String tipo;
//		private String detinatario;
//		public SaludaEvento(String tipo, String detinatario) {
//			this.tipo = tipo;
//			this.detinatario = detinatario;
//		}
//		public String tipo() {
//			return tipo;
//		}
//		public String detinatario() {
//			return detinatario;
//		}		
//	}
	public static record SaludaEvent(String tipo,  String detinatario) { }
	
	private ApplicationEventPublisher publisher;
	private Entorno entorno;
	
	public SaludaImpl(Entorno entorno, ApplicationEventPublisher publisher) {
		this.entorno = entorno;
		this.publisher = publisher;
	}

	protected void onSaluda(@NonNull String tipo, @NonNull String detinatario) {
		publisher.publishEvent(new SaludaEvent(tipo, detinatario));
	}
	
	@Override
	public void saluda(@NonNull String nombre) {
//		if(nombre == null)
//			throw new IllegalArgumentException("El nombre es obligatorio.");
		entorno.write("Hola " + nombre.toUpperCase());
		onSaluda("saluda", nombre);
	}
	public void saluda() {
		entorno.write("Hola");
		onSaluda("saluda", "sin nombre");
	}

	@Override
	public int getContador() {
		return entorno.getContador();
	}

	
	public Optional<Entorno> getEntorno() {
		return Optional.ofNullable(entorno);
	}

}
