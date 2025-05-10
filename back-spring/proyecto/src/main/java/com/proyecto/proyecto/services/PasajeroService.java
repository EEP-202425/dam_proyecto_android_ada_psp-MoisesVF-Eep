package com.proyecto.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repositories.IPasajeroRepository;
import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Destinos;
import com.proyecto.proyecto.tablas.Pasajero;
import com.proyecto.proyecto.tablas.Rutas;

@Service
public class PasajeroService {

	@Autowired
	IPasajeroRepository pasajeroRepository;

	public ArrayList<Pasajero> getPasajeros() {
		return (ArrayList<Pasajero>) pasajeroRepository.findAll();
	}

	public Pasajero savePsajero(Pasajero pasajero) {
		return pasajeroRepository.save(pasajero);
	}

	public Optional<Pasajero> getById(Long id) {
		return pasajeroRepository.findById(id);
	}

	public Pasajero updateById(Pasajero nuevoPasajero, Long id) {
		Pasajero pasajero = pasajeroRepository.findById(id).get();

		pasajero.setNombre(nuevoPasajero.getNombre());
		pasajero.setApellido(nuevoPasajero.getApellido());
		pasajero.setEmail(nuevoPasajero.getEmail());
		pasajero.setTelefono(nuevoPasajero.getTelefono());

		return pasajero;
	}

	public Boolean deletePasajero(Long id) {
		try {
			pasajeroRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	public Long obtenerId(Pasajero pasajero) {
	    Optional<Pasajero> pasajeroExistente = pasajeroRepository.findByNombreAndApellidoAndEmailAndTelefono(
	        pasajero.getNombre(),
	        pasajero.getApellido(),
	        pasajero.getEmail(),
	        pasajero.getTelefono()
	    );

	    if (pasajeroExistente.isPresent()) {
	        return pasajeroExistente.get().getId();
	    } else {
	        throw new RuntimeException("Pasajero no encontrado");
	    }
	}


}
