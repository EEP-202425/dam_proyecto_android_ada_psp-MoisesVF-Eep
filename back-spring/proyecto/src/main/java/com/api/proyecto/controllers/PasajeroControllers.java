package com.api.proyecto.controllers;

import java.security.PublicKey;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.proyecto.models.Pasajero;
import com.api.proyecto.services.PasajeroService;

@RestController
@RequestMapping("/pasajero")
public class PasajeroControllers {

	@Autowired
	private PasajeroService ps;

	@GetMapping
	public List<Pasajero> getPasajeros() {
		return ps.getPasajeros();
	}

	@PostMapping
	public Pasajero saveUser(@RequestBody Pasajero pasajero) {
		ps.savePasajero(pasajero);
		return pasajero;
	}

	@GetMapping(path = "/{id}")
	public Optional<Pasajero> getPasajeroPorID(@PathVariable("id") Long id) {
		return ps.obtenerPasajeroPorId(id);
	}

	@PutMapping(path = "/{id}")
	public Pasajero updatePasajeroPorId(@RequestBody Pasajero pasajero, @PathVariable("id") Long id) {
		return ps.updatePasajero(pasajero, id);
	}

	@DeleteMapping(path = "/{id}")
	public String deletePasajero(@PathVariable("id") Long id) {
		boolean borrado = ps.deletePasajero(id);

		if (borrado) {
			return "Pasajero con id " + id + " borrado";
		} else {
			return "Pasajero con id " + id + " no se ha podido borrar";

		}

	}
}
