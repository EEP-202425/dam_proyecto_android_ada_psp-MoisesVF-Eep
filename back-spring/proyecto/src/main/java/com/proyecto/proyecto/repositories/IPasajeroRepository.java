package com.proyecto.proyecto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.tablas.Pasajero;

@Repository
public interface IPasajeroRepository extends CrudRepository<Pasajero, Long> {

}
