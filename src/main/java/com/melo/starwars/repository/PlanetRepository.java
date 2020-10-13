package com.melo.starwars.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.melo.starwars.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	Planet findById(ObjectId id);// ta funcionando só com esse
	//List<Planet> findByName(String name);
	
}
