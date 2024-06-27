package com.example;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}
	
//	@Autowired
//	FilmRepository dao;
//	@Autowired
//	FilmService srv;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
//		var item = new Film(0, "Test", new Language(1), (byte)1, new BigDecimal(1.0), new BigDecimal(10.5));
//		item.addActor(new Actor(1));
//		item.addActor(new Actor(2));
//		item.addActor(new Actor(3));
//		item.addCategory(1);
//		item.addCategory(2);
//		dao.save(item);
//		System.err.println(item);
//		item = dao.findAll().getLast();
//		item.setTitle("KKKKKKK");
//		item.removeActor(new Actor(1));
//		item.addActor(new Actor(4));
//		item.removeCategory(new Category(2));
//		item.addCategory(3);
//		dao.save(item);
//		System.err.println(item);
//		item = dao.findAll().getLast();
//		dao.delete(item);
//		srv.getAll((root, query, builder) -> builder.lessThan(root.get("length"), 50)).forEach(System.out::println);
//		srv.getAll((root, query, builder) -> builder.and(
//				builder.equal(root.get("rating"), Rating.GENERAL_AUDIENCES),
//				builder.greaterThan(root.get("rentalDuration"), 5))
//			).forEach(System.out::println);
	}

}
