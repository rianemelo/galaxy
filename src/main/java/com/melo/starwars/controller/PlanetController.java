package com.melo.starwars.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;

import com.melo.starwars.model.Planet;
import com.melo.starwars.repository.PlanetRepository;
import com.melo.starwars.service.PlanetService;


@RestController
@RequestMapping("/planets")
public class PlanetController {
	
	@Autowired 
	PlanetRepository repository;
	
	@Autowired 
	PlanetService service;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet, HttpServletResponse response) {
		Planet savedPlanet = service.addPlanet(planet);
		if (savedPlanet == null) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPlanet);	
		
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public List<Planet> findAll() {
		return service.findAll();
	}	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
	}
	
	 /*@RequestMapping(value = "/{name}", method = RequestMethod.GET) 
	 public ResponseEntity<List<Planet>> findByName(@PathVariable("name") String name) {
	 List<Planet> planets = service.findByName(name); 
	 if (planets == null) {
		 return ResponseEntity.notFound().build();
	 } 
	 return new ResponseEntity<>(planets, HttpStatus.OK); 
	 }*/
	 
	
	 
	/* @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	 public ResponseEntity<List<Planet>> findByNome(@PathVariable("name") String name) {
		 List<Planet> planets = service.findByName(name);

		 if (planets != null) {
			 return new ResponseEntity<>(planets, HttpStatus.OK);
		 } else {
			 return ResponseEntity.notFound().build();
		 }
	 } */
	
	 
	 /*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 public ResponseEntity<Optional<Planet>> findById(@PathVariable("id") ObjectId id) {
		 Optional<Planet> planet = service.findById(id);//.toHexString());
		 if (!planet.isPresent()) {
			 ResponseEntity.notFound().build();
		 } 
		 return ResponseEntity.status(HttpStatus.OK).body(planet);
	 }*/

	 //ta funcionando
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Planet> findById(@PathVariable("id") ObjectId id) {
		Planet planet = service.findById(id);
		if (planet == null) {
			ResponseEntity.notFound().build();
		} 
		return ResponseEntity.status(HttpStatus.OK).body(planet);
	}
	
}
