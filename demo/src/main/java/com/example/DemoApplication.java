package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorOtroDTO;
import com.example.domains.entities.models.ActorShort;
import com.example.ioc.Entorno;
import com.example.ioc.Rango;
import com.example.ioc.Saluda;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Autowired
//	ActorService srv;
//	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
//		srv.getByProjection(ActorDTO.class).forEach(System.out::println);
	}

	/*
	@Autowired
	ActorRepository dao;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
//		dao.findAll().forEach(System.out::println);
//		var item = dao.findById(301);
//		if(item.isEmpty()) {
//			System.err.println("No encontrado");
//		} else {
//			System.out.println(item.get());
//		}
//		var actor = new Actor(0, "Pepito", "Grillo");
//		System.out.println(dao.save(actor));
//		var item = dao.findById(201);
//		if(item.isEmpty()) {
//			System.err.println("No encontrado");
//		} else {
//			var actor = item.get();
//			actor.setFirstName(actor.getFirstName().toUpperCase());
//			dao.save(actor);
//		}
//		dao.deleteById(201);
//		dao.findAll().forEach(System.out::println);
//		dao.findTop5ByLastNameStartingWithOrderByFirstNameDesc("P").forEach(System.out::println);
//		dao.findTop5ByLastNameStartingWith("P", Sort.by("LastName").ascending()).forEach(System.out::println);
//		dao.findByActorIdGreaterThanEqual(200).forEach(System.out::println);
//		dao.findByJPQL(200).forEach(System.out::println);
//		dao.findBySQL(200).forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("actorId"), 200)).forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.lessThan(root.get("actorId"), 10)).forEach(System.out::println);
//		var item = dao.findById(1);
//		if(item.isEmpty()) {
//			System.err.println("No encontrado");
//		} else {
//			var actor = item.get();
//			System.out.println(actor);
//			actor.getFilmActors().forEach(f -> System.out.println(f.getFilm().getTitle()));
//		}
//		var actor = new Actor(0, null, "12345678Z");
//		if(actor.isValid()) {
//		System.out.println(dao.save(actor));
//		} else {
//			actor.getErrors().forEach(System.out::println);
//		}
//		var actor = new ActorDTO(0, "FROM", "DTO");
//		dao.save(ActorDTO.from(actor));
//		dao.findAll().forEach(item -> System.out.println(ActorDTO.from(item)));
//		dao.readByActorIdGreaterThanEqual(200).forEach(System.out::println);
//		dao.queryByActorIdGreaterThanEqual(200).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findByActorIdGreaterThanEqual(200, ActorDTO.class).forEach(System.out::println);
//		dao.findByActorIdGreaterThanEqual(200, ActorShort.class).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findAll(PageRequest.of(3, 10, Sort.by("ActorId"))).forEach(System.out::println);
//		var serializa = new ObjectMapper();
//		dao.findByActorIdGreaterThanEqual(200, ActorDTO.class).forEach(item -> {
//			try {
//				System.out.println(serializa.writeValueAsString(item));
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		var serializa = new XmlMapper();;
		dao.findAll(PageRequest.of(3, 10, Sort.by("ActorId"))).forEach(item -> {
			try {
				System.out.println(serializa.writeValueAsString(item));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	*/
	/*
	 * @Autowired // @Qualifier("es") Saluda saluda;
	 * 
	 * @Autowired // @Qualifier("en") Saluda saluda2;
	 * 
	 * @Autowired Entorno entorno;
	 * 
	 * @Autowired private Rango rango;
	 * 
	 * // @Autowired(required = false) // SaludaEnImpl kk;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * System.err.println("Aplicación arrancada..."); // var saluda = new Saluda();
	 * System.out.println(saluda.getContador()); saluda.saluda("Mundo"); //
	 * saluda.saluda(null); saluda2.saluda("Mundo");
	 * System.out.println(saluda.getContador());
	 * System.out.println(saluda2.getContador());
	 * System.out.println(entorno.getContador()); System.out.println(rango.getMin()
	 * + " -> " + rango.getMax()); }
	 */
}
