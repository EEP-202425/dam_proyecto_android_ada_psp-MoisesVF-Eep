package com.proyecto.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	    public ResponseEntity<Billete> createBillete(@RequestBody Billete billete) {
	        Billete savedBillete = billeteService.saveBillete(billete);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedBillete);
	    }
	
    @PostMapping("/pasajero/{pasajeroId}")
    public ResponseEntity<Billete> crearBillete(@PathVariable Long pasajeroId,
                                                @RequestBody Billete billete) {
        Billete nuevo = billeteService.crearBilleteParaPasajero(pasajeroId, billete);
        return ResponseEntity.ok(nuevo);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Long> getBilleteId(@PathVariable Long id){
    	Optional<Long>billeteId = billeteService.getIdBillete(id);
    	if(billeteId.isPresent()) {
    		return ResponseEntity.ok(billeteId.get());
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }

}
