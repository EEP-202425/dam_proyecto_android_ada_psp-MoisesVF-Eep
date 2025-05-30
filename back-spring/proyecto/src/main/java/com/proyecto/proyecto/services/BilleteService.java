package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repositories.IBilleteRepository;
import com.proyecto.proyecto.repositories.IPasajeroRepository;
import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Pasajero;

@Service
public class BilleteService {
	@Autowired
	private IPasajeroRepository pasajeroRepository;

	@Autowired
	private IBilleteRepository billeteRepository;

	public List<Billete> obtenerBilletesPorPasajero(Long pasajeroId) {
		return billeteRepository.findByPasajeroId(pasajeroId);
	}

	public Billete saveBillete(Billete billete) {
		return billeteRepository.save(billete);
	}

	public Boolean deleteBillete(Long id) {
		try {
			billeteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	
	public Billete crearBilleteParaPasajero(Long pasajeroId, Billete billete) {
       
        Pasajero pasajero = pasajeroRepository.findById(pasajeroId)
            .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        
        billete.setPasajero(pasajero);


      
        return billeteRepository.save(billete);
    }
	
	public Optional<Billete> getById(Long id) {
		return billeteRepository.findById(id);
	}
}
