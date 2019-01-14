package com.b2w.Planets.api.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.b2w.Planets.api.model.Planeta;



public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	@Query(value = "{name:?0}")
	Planeta findName(String name);
	
	
	@Query(value = "{id:?0}")
	Planeta FindId(String id);
	
	

}
 