package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ActorDTO implements Serializable {
	@JsonProperty("id")
	private int actorId;
	@JsonProperty("nombre")
	@NotBlank
	@Size(max = 45)
	private String firstName;
	@JsonProperty("apellidos")
	private String lastName;

	public static ActorDTO from(Actor source) {
		return new ActorDTO(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
	public static Actor from(ActorDTO source) {
		return new Actor(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
}
