package com.proyecto.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.repositories.IBilleteRepository;
import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Pasajero;

@Service
public class BilleteService {

	@Autowired
	private IBilleteRepository billeteRepository;

	public List<Billete> obtenerBilletesPorPasajero(Long pasajeroId) {
		return billeteRepository.findAllByPasajeroId(pasajeroId);
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
}
