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

    public List<Billete> obtenerBilletesPorPasajero(Pasajero pasajero) {
        return billeteRepository.findByPasajero(pasajero);
    }

}
