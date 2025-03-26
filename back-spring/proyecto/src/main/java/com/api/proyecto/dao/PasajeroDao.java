package com.api.proyecto.dao;

import org.springframework.stereotype.Repository;

import com.api.proyecto.models.Pasajero;
@Repository
public class PasajeroDao extends AbstractDao<Pasajero>{

	public PasajeroDao() {
		setClazz(Pasajero.class);
	}

	
	

	
	
}
