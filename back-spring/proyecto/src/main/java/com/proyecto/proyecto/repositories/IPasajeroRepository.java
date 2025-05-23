package com.proyecto.proyecto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.tablas.Billete;
import com.proyecto.proyecto.tablas.Pasajero;

@Repository
public interface IPasajeroRepository extends CrudRepository<Pasajero, Long> {
		
	Optional<Pasajero> findByNombreAndApellidoAndEmailAndTelefono(
		    String nombre,
		    String apellido,
		    String email,
		    String telefono
		);
}
