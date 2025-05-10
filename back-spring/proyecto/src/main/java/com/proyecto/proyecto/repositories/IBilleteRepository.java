package com.proyecto.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.tablas.Billete;

@Repository
public interface IBilleteRepository extends JpaRepository<Billete, Long> {
	 List<Billete> findByPasajeroId(Long pasajeroId);

}
