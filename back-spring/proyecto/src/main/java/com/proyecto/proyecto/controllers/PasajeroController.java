package com.proyecto.proyecto.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proyecto.proyecto.services.PasajeroService;
import com.proyecto.proyecto.tablas.Pasajero;

@RestController
@RequestMapping("/pasajero")
public class PasajeroController {

	@Autowired
	private PasajeroService ps;

	@GetMapping
	public ArrayList<Pasajero> getPasajeros() {
		return this.ps.getPasajeros();
	}

	@PostMapping
	public ResponseEntity<Pasajero> savePasajero(@RequestBody Pasajero pasajero,UriComponentsBuilder ucb) {

	    Pasajero savedPasajero = ps.savePsajero(pasajero);
	    URI location = ucb.path("/pasajeros/{id}")
	                      .buildAndExpand(savedPasajero.getId())  
	                      .toUri();
	    
	    return ResponseEntity
	            .created(location)           
	            .body(savedPasajero);       
	}


	@GetMapping(path = "/{id}")
	public ResponseEntity<Pasajero> getPasajeroById(@PathVariable Long id) {
		Optional<Pasajero>pasajeroOpt = ps.getById(id);
		if(pasajeroOpt.isPresent()) {
			return ResponseEntity.ok(pasajeroOpt.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(path = "/{id}")
	public Pasajero updateById(@RequestBody Pasajero nuevoPasajero, @PathVariable Long id) {
		return this.ps.updateById(nuevoPasajero, id);
	}

	@DeleteMapping(path = "/{id}")
	public String deletePasajeroById(@PathVariable Long id) {
		return this.ps.deletePasajero(id) ? "Pasajero con id:" + id + " Borrado"
				: "Pasajero con id:" + id + " No se borro";

	}

}
