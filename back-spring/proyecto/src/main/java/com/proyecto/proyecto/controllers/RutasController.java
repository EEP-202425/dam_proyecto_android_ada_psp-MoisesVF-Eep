package com.proyecto.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proyecto.proyecto.services.RutasService;

import com.proyecto.proyecto.tablas.Rutas;

@RestController
@RequestMapping("/ruta")
public class RutasController {
	
	@Autowired
	private RutasService RS;
	
	@GetMapping
	public ResponseEntity<List<Rutas>>getRutas(){
		return ResponseEntity.ok(RS.getRutas());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Rutas>getRutasById(@PathVariable Long id){
		Optional<Rutas> rutasOpt = RS.getById(id);
		if(rutasOpt.isPresent()) {
			return ResponseEntity.ok(rutasOpt.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Rutas> saveRuta(@RequestBody Rutas ruta, UriComponentsBuilder ucb){
		
		Rutas saveRuta = RS.saveRuta(ruta);
		URI location = ucb.path("/ruta/{id}").buildAndExpand(saveRuta.getId()).toUri();

		return ResponseEntity.created(location).body(saveRuta);
	}
	
	

	

}
