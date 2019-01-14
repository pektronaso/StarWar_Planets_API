package com.b2w.Planets.api.services;

import java.util.List;

import com.b2w.Planets.api.model.Planeta;
import com.b2w.Planets.api.responses.Response;

public interface PlanetaService {	
	
	Response<Planeta>  FindId (String id) throws Exception;
	
	Planeta findName(String name) throws Exception;
	
	Planeta cadastrar(Planeta planeta);
	
	Planeta atualizar(Planeta planeta);
	
	void remover(String id);

	int get_Aparicoes(String name) throws Exception;

	int get_numberOfItems();

	Response<List<Planeta>> ListAll(int page) throws Exception;
	
	
	
	
}
