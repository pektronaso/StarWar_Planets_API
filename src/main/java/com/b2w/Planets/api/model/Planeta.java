package com.b2w.Planets.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Planeta")
public class Planeta {

	@Id
	private String id;
	private String name;
	private String climate;	
	private String terrain;
	private List<String> films;
	private int aparicoes;
	
	
	public Planeta(String name, String climate, String terrain) {
		
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		
	}
	
	
	
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
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




	public void setTerreno(String terrain) {
		this.terrain = terrain;
	}




	public Planeta() { 
		
		
		
	}




	public int getAparicoes() {
		return aparicoes;
	}




	public void setAparicoes(int aparicoes) {
		this.aparicoes = aparicoes;
	}




	public List<String> getFilms() {
		return films;
	}




	public void setFilms(List<String> films) {
		this.films = films;
	}




	@Override
	public String toString() {
		return "Planeta [id=" + id + ", name=" + name + ", climate=" + climate + ", terrain=" + terrain + ", films="
				+ films + ", aparicoes=" + aparicoes + "]";
	}







	
	
	
}
