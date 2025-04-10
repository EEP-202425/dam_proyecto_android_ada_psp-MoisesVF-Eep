package com.proyecto.proyecto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.tablas.Rutas;

@Repository
public interface IRutasRepository extends CrudRepository<Rutas, Long>{

}
