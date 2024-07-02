package com.example.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actores/v1")
public class ActorResource {
	private ActorService srv;

	public ActorResource(ActorService srv) {
		this.srv = srv;
	}
	
	@GetMapping
	public List<?> getAll(@RequestParam(required = false, defaultValue = "largo") String modo) {
		if("short".equals(modo))
			return srv.getByProjection(ActorShort.class);
		else
			return srv.getByProjection(ActorDTO.class); // srv.getAll();
	}
	
	@GetMapping(params = "page")
	public Page<ActorShort> getAll(Pageable page) {
		return srv.getByProjection(page, ActorShort.class);
	}
	
	@GetMapping(path = "/{id}")
	public ActorDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return ActorDTO.from(item.get());
	}
	
	@GetMapping(path = "/{id}/v2")
	public Optional<Actor> getOneV2(@PathVariable int id) {
		return srv.getOne(id);
	}
	
	@GetMapping(path = "/{id}/v3")
	public ResponseEntity<Actor> getOneV3(@PathVariable int id) {
		return ResponseEntity.of(srv.getOne(id));
	}
	@GetMapping(path = "/{id}/v4")
	public ResponseEntity<ActorDTO> getOneV4(@PathVariable int id) throws NotFoundException {
		// return ResponseEntity.of(srv.getOne(id));
		var item = srv.getOne(id);
		if(item.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(ActorDTO.from(item.get()));
	}
	
	record Peli(int id, String titulo) {}
	
	@GetMapping(path = "/{id}/pelis")
	@Transactional
	public List<Peli> getPelis(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get().getFilmActors().stream()
				.map(o -> new Peli(o.getFilm().getFilmId(), o.getFilm().getTitle()))
				.toList();
	}
	
	@DeleteMapping(path = "/{id}/jubilacion")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void jubilar(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
		item.get().jubilate();
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(ActorDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws NotFoundException, InvalidDataException, BadRequestException {
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(ActorDTO.from(item));
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
