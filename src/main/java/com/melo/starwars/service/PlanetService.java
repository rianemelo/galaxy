package com.melo.starwars.service;

import org.bson.types.ObjectId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.melo.starwars.model.Planet;
import com.melo.starwars.repository.PlanetRepository;

@Service
public class PlanetService {
	
	@Autowired
	PlanetRepository repository;
	
	public Planet addPlanet(Planet planet) {
		Integer number = this.getNumberFilms(planet.getName());
		if(number == null) {
			return null;
		}
		planet.setId(ObjectId.get());
		planet.setFilms(number);
		return repository.save(planet);
	}
	
	public List<Planet> findAll() {
		return repository.findAll();
	}
		
	public Integer getNumberFilms(String name) {		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Object object;
		try {
			object = restTemplate.exchange("https://swapi.dev/api/planets/?search=" + name, HttpMethod.GET,
								new HttpEntity<String>("parameters", headers), Object.class);
		} catch (Exception e) {
			return null;
		}
		
		Gson gson = new Gson();
		JsonArray results = gson.fromJson(gson.toJson(object), JsonObject.class)
							.getAsJsonObject("body")
							.getAsJsonArray("results"); //results é uma lista de objetos na response da api swapi
		JsonElement correctResult = null;
		
		for (JsonElement z: results) {
			if(z.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)) {
				correctResult = z;
			}
		}
		
		if (correctResult == null) {
			return 0;
		} else {
			return correctResult.getAsJsonObject().getAsJsonArray("films").size(); //films é um array de String
		}
	}
		
	
	//public List<Planet> findByName(String name) {
		//return repository.findByName(name);
	//}
	
	/*public List<Planet> findByName(String name) {
		if (name == null) {
			return null;
		}
		Planet planet = new Planet();
		planet.setName(name);
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
			 
		Example<Planet> example = Example.of(planet, matcher);
			 
		return repository.findAll(example);
		 
	}*/

	
	/*public Optional<Planet> findById(ObjectId id) {
		Optional<Planet> savedPlanet = repository.findById(id.toHexString());//.toHexString()); 
		if (!savedPlanet.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		} 
		return savedPlanet; 
	}*/
	
	
	
	//ta funcionando
	public Planet findById(ObjectId id) {
		Planet savedPlanet = repository.findById(id); 
		if (savedPlanet == null) {
			throw new
			EmptyResultDataAccessException(1);
		} 
		return savedPlanet; 
	}

	
}