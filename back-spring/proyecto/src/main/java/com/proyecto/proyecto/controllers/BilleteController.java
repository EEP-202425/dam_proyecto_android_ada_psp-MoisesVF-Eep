package com.proyecto.proyecto.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proyecto.proyecto.services.BilleteService;
import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Pasajero;

@RestController
@RequestMapping("/billete")
public class BilleteController {

	@Autowired
	private BilleteService billeteService;

	@GetMapping("/pasajero/{id}")
	public ResponseEntity<List<Billete>> findBilletes(@PathVariable Long id) {
		return ResponseEntity.ok(billeteService.obtenerBilletesPorPasajero(id));
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteBilleteById(@PathVariable Long id) {
		return this.billeteService.deleteBillete(id) ? "Billete con id:" + id + " Borrado"
				: "Billete con id:" + id + " No se borro";

	}
	
	@PostMapping
	public ResponseEntity<Billete> savedBillete(@RequestBody Billete billete, UriComponentsBuilder ucb) {

		Billete saveBillete = billeteService.saveBillete(billete);
		URI location = ucb.path("/billete/{id}").buildAndExpand(saveBillete.getId()).toUri();

		return ResponseEntity.created(location).body(saveBillete);
	}

}
