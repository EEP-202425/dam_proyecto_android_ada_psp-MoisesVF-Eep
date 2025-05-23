package com.proyecto.proyecto.controllers;

import java.net.URI;

import java.util.List;
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

import com.proyecto.proyecto.repositories.IBilleteRepository;
import com.proyecto.proyecto.services.PasajeroService;
import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Destinos;
import com.proyecto.proyecto.tablas.Pasajero;
import com.proyecto.proyecto.tablas.Rutas;

@RestController
@RequestMapping("/pasajero")
public class PasajeroController {
	
	
	@Autowired
	private IBilleteRepository billeteRepository;
	@Autowired
	private PasajeroService ps;
	
	@GetMapping
	public ResponseEntity<List<Pasajero>> getPasajeros() {
		return ResponseEntity.ok(ps.getPasajeros());
	}

	@PostMapping
	public ResponseEntity<Pasajero> savePasajero(@RequestBody Pasajero pasajero, UriComponentsBuilder ucb) {

		Pasajero savedPasajero = ps.savePsajero(pasajero);
		URI location = ucb.path("/pasajero/{id}").buildAndExpand(savedPasajero.getId()).toUri();

		return ResponseEntity.created(location).body(savedPasajero);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pasajero> getPasajeroById(@PathVariable Long id) {
		Optional<Pasajero> pasajeroOpt = ps.getById(id);
		if (pasajeroOpt.isPresent()) {
			return ResponseEntity.ok(pasajeroOpt.get());
		} else {
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
	@GetMapping("/{id}/billetes")
    public ResponseEntity<List<Billete>> getBilletesByPasajero(@PathVariable Long id) {
        List<Billete> billetes = billeteRepository.findByPasajeroId(id);
        return ResponseEntity.ok(billetes);
    }
	@PostMapping("/id")
	public ResponseEntity<Long> getIdPasajero(@RequestBody Pasajero pasajero) {
	    return ResponseEntity.ok(ps.obtenerId(pasajero));
	}

	
	

}
