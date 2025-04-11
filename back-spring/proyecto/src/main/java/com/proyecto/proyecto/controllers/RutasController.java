package com.proyecto.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proyecto.proyecto.services.RutasService;
import com.proyecto.proyecto.tablas.Destinos;
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
	
	@GetMapping(path = "/{Origen}")
	public ResponseEntity<List<Rutas>>getRutasByCiudad(@PathVariable String Origen){
		Destinos Origenes = transformador(Origen);
		List<Rutas> rutas = RS.getByCiudad(Origenes);
		if(!rutas.isEmpty()) {
			return ResponseEntity.ok(rutas);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	private Destinos transformador(String Origen) {
	    System.out.println("Recibiendo ciudad: " + Origen); 
	    Origen = Origen.trim().toLowerCase();
	    for (Destinos destino : Destinos.values()) {
	        if (destino.getCiudad().equalsIgnoreCase(Origen)) {
	            return destino;
	        }
	    }

	    throw new IllegalArgumentException("Ciudad de origen no válida: " + Origen);
	}

	@PostMapping
	public ResponseEntity<Rutas> saveRuta(@RequestBody Rutas ruta, UriComponentsBuilder ucb){
		
		Rutas saveRuta = RS.saveRuta(ruta);
		URI location = ucb.path("/ruta/{id}").buildAndExpand(saveRuta.getId()).toUri();

		return ResponseEntity.created(location).body(saveRuta);
	}
	
	

	

}
