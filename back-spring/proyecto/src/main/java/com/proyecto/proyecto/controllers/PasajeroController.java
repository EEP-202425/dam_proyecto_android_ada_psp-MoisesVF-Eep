package com.proyecto.proyecto.controllers;

import java.util.ArrayList;
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
	public Pasajero savePasajero(@RequestBody Pasajero pasajero) {
		return this.ps.savePsajero(pasajero);
	}

	@GetMapping(path = "/{id}")
	public Optional<Pasajero> getPasajeroById(@PathVariable Long id) {
		return this.ps.getById(id);
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
