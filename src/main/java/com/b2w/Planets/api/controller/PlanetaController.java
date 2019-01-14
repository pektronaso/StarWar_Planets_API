package com.b2w.Planets.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.b2w.Planets.api.responses.Response;
import com.b2w.Planets.api.services.PlanetaService;


import com.b2w.Planets.api.model.Planeta;



@RestController
@RequestMapping("planeta")
public class PlanetaController {

	@Autowired
	private PlanetaService PlanetaService;
	

	
	
	
	
	
	
	@GetMapping
	public ResponseEntity<Response<List<Planeta>>> listAll() throws Exception {
		
		Response<List<Planeta>> response = this.PlanetaService.ListAll(0);
		
		return ResponseEntity.ok(response);
		
		}
	
	
	
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<Response<List<Planeta>>> listAll(@PathVariable("page") int page) throws Exception {
		
			
		
		Response<List<Planeta>> response = this.PlanetaService.ListAll(page);
		
		return ResponseEntity.ok(response);
		
		}
	
	
	
	@GetMapping(path = "/{id}")
	public Response<Planeta> listById(@PathVariable(name = "id") String id) throws Exception { 
		
		Response<Planeta> response = this.PlanetaService.FindId(id);

		return response;
		
	}
	

	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)	
	public  ResponseEntity<Response<Planeta>> listByName(@PathVariable(name = "name") String name) throws Exception {
		return ResponseEntity.ok( new Response<Planeta>(this.PlanetaService.findName(name)));
		
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Response<Planeta>> cadastrar(@RequestBody Planeta planeta, BindingResult result) { 
		
		if (result.hasErrors()) { 
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Planeta>(erros));			
		}
		
		return ResponseEntity.ok(new Response<Planeta>(this.PlanetaService.cadastrar(planeta)));
		
		
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Planeta>> atualizar (@PathVariable(name = "id") String id, @RequestBody Planeta planeta, BindingResult result) { 
		if(result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Planeta>(erros));
		}
		
		planeta.setId(id);
		return ResponseEntity.ok(new Response<Planeta>(this.PlanetaService.atualizar(planeta)));
		
	}
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Boolean> remover(@PathVariable(name = "id") String id) { 
		this.PlanetaService.remover(id);
		return ResponseEntity.ok(true);
		
		
	}
	
	
	
	
	
	

	
}
