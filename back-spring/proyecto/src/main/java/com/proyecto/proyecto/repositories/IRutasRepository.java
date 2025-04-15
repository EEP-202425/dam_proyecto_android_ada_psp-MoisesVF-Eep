package com.proyecto.proyecto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.tablas.Destinos;
import com.proyecto.proyecto.tablas.Rutas;

@Repository
public interface IRutasRepository extends CrudRepository<Rutas, Long> {

	List<Rutas> findByOrigen(Destinos origen);

}
