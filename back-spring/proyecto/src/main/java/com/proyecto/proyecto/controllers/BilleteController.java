package com.proyecto.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.services.BilleteService;
import com.proyecto.proyecto.tablas.Billete;

@RestController
@RequestMapping("/billete")
public class BilleteController {

	@Autowired
	private BilleteService billeteService;

	@GetMapping("/billetes/{id}")
	public ResponseEntity<List<Billete>> findBilletes(@PathVariable Long id) {
		return ResponseEntity.ok(billeteService.obtenerBilletesPorPasajero(id));
	}

}
