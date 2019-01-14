package com.b2w.Planets.api.services;


import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.Planets.api.services.PlanetaService;


import com.b2w.Planets.api.model.data;
import com.b2w.Planets.api.PlanetsApplication;
import com.b2w.Planets.api.model.Planeta;
import com.b2w.Planets.api.repositories.PlanetaRepository;
import com.b2w.Planets.api.responses.Response;;



@Service
public class PlanetaServiceImplementation implements PlanetaService {
	
	
	private static final Logger log = LoggerFactory.getLogger(PlanetsApplication.class);
	
	
	
	
	@Autowired
	private PlanetaRepository PlanetaRepository;
	
	
	

	
	
	@Override
	public Response<List<Planeta>> ListAll(int page) throws Exception {
		
		if (page == 0) page++;
		
		page--;
		
		Page<Planeta> pageData = this.PlanetaRepository.findAll(PageRequest.of(page, 5));
		
		List<Planeta> finalList = pageData.getContent();		
		
		
		for (Planeta planeta : finalList) {
			
			planeta.setAparicoes(get_Aparicoes(planeta.getName()));
			
		}
		
		
		
		Response<List<Planeta>> response = new Response<List<Planeta>>(finalList);
		
		response.setTotal_items(pageData.getTotalElements());
		response.setPages(pageData.getTotalPages());
		response.setPagina((page + 1) + " of " + (pageData.getTotalPages()));
		
		
		
		
		return response;
		
		
	}
	
	
	
	
	
	

	@Override
	public Response<Planeta> FindId(String id) throws Exception  {
		
		Planeta planeta = this.PlanetaRepository.FindId(id);	

		
		
		
		
		if (planeta != null) {
		
		planeta.setAparicoes(get_Aparicoes(planeta.getName()));
		

		Response<Planeta> response = new Response<Planeta>(planeta);
		
		response.setTotal_items(1);
		response.setPages(1);
		response.setPagina("1 of 1");

		return response;

		} else { 
		
		planeta = this.PlanetaRepository.findName(id);
		planeta.setAparicoes(get_Aparicoes(planeta.getName()));

		Response<Planeta> response = new Response<Planeta>(planeta);
		
		response.setTotal_items(1);
		response.setPages(1);
		response.setPagina("1 of 1");

		return response;	

		}
	}

	
	
	@Override
	public Planeta cadastrar(Planeta planeta) {
	
		return this.PlanetaRepository.save(planeta);
	}
	

	@Override
	public Planeta atualizar(Planeta planeta) {
		
		return this.PlanetaRepository.save(planeta);

	}

	
	@Override
	public void remover(String id) {
		this.PlanetaRepository.deleteById(id);

	}

	@Override
	public Planeta findName(String name) throws Exception {
			
		Planeta planeta = this.PlanetaRepository.findName(name);
		
		planeta.setAparicoes(get_Aparicoes(planeta.getName()));
		
		return planeta;
			
			
		
	}

	
	
	
	private int aparicoes;
	
	public int get_Aparicoes(String name) throws Exception {
		
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);		
        

        data data = restTemplate.getForObject("https://swapi.co/api/planets", data.class);
        
        
        aparicoes = 0;
        
	    int TotalPlanets = data.getCount();
		
		int ViewsPerPage = 10;
		
		int Toview = TotalPlanets / ViewsPerPage;
		
		int resto = TotalPlanets % ViewsPerPage;
		
		if (resto > 0) Toview++;
		
		
		
		for (int i = 1; i <= Toview; i++) {
			
			data atual_Data = restTemplate.getForObject("https://swapi.co/api/planets/?page=" + i, data.class);
			List<Planeta> atual_Planetas = atual_Data.getResults();
			
			
			atual_Planetas.forEach(
					
					planeta -> {
					
						if (planeta.getName().equals(name)) {
							
						  log.info("Planeta " + planeta.getName() + " localizado em Public StarWars API.");
						  log.info("Calculando Quantidade de Aparições em Filmes.");
						  aparicoes = planeta.getFilms().size(); 
						  
						  
						  
						} 
						 
					} 
					
					);
			
			
			
			
		}
		
		return aparicoes;


	    
	    
	}
	
	
	



	
	
	

	
}
