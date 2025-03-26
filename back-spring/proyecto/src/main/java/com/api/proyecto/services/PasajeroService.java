package com.api.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.proyecto.dao.PasajeroDao;
import com.api.proyecto.models.Pasajero;

@Service
public class PasajeroService {

	@Autowired
	private PasajeroDao pasajeroDao;

	
	

	public Optional<Pasajero> obtenerPasajeroPorId(long id) {
		return this.pasajeroDao.get(id);
	}

	public List<Pasajero> getPasajeros() {
		return this.pasajeroDao.getAll();
	}

	public void savePasajero(Pasajero pasajero) {
		this.pasajeroDao.save(pasajero);
	}

	public Pasajero updatePasajero(Pasajero actualizacion, Long id) {
		Pasajero pas = pasajeroDao.get(id).get();
		pas.setNombre(actualizacion.getNombre());
		pas.setApellido(actualizacion.getApellido());
		pas.setEmail(actualizacion.getEmail());
		pas.setTelefono(actualizacion.getTelefono());

		return pas;

	}

	public Boolean deletePasajero(Long id) {
		if (pasajeroDao.existe(id)) {
			pasajeroDao.delete(id);
			return true;
		} else {
			return false;
		}

	}
}
