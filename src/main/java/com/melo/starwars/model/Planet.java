package com.melo.starwars.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "planets")
public class Planet {

	@Id
	private ObjectId id;
	
	private String name;
	private String climate;
	private String terrain;
	private Integer films; //quantidade de filmes em que o planeta apareceu
	
	public Planet() {
	}

	public String getId() { 
		return id.toHexString(); 
		}
	public void setId(ObjectId id) {
		this.id = id;
		}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getFilms() {
		return films;
	}
	public void setFilms(Integer films) {
		this.films = films;
	}
	
	/*@Override 
	public String toString() {
		return "Planet [id=" + id + ", name=" + name + ", climate=" + climate + ", terrain=" + terrain
				+ ", films=" + films + "]";	
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Planet otherPlanet = (Planet) obj;
		if (id == null) {
			if (otherPlanet.id != null) {
				return false;
			}
		}else if (!id.equals(otherPlanet.id)) {
			return false;
		} 
		return true;
	}*/
		
}	