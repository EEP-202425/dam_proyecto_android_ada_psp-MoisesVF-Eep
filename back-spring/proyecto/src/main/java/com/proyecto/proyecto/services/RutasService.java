package com.proyecto.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repositories.IRutasRepository;
import com.proyecto.proyecto.tablas.Destinos;
import com.proyecto.proyecto.tablas.Rutas;

@Service
public class RutasService {
	
	@Autowired
	IRutasRepository rutasRepository;
	
	public ArrayList<Rutas> getRutas() {
		return (ArrayList<Rutas>) rutasRepository.findAll();
	}

	public Rutas saveRuta(Rutas ruta) {
		return rutasRepository.save(ruta);
	}

	public List<Rutas> getByCiudad(Destinos Origen) {
	    if (Origen == null) {
	        throw new IllegalArgumentException("El origen no puede ser null");
	    }

	    List<Rutas> rutas = rutasRepository.findByOrigen(Origen);
	    if (rutas.isEmpty()) {
	        throw new RuntimeException("No se encontraron rutas para el origen especificado");
	    }

	    return rutas;
	}
	
	
	public Boolean deleteRuta(Long id) {
		try {
			rutasRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
