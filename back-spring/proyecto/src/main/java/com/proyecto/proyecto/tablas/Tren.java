package com.proyecto.proyecto.tablas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trenes")
public class Tren {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NombreTren nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoTren tipo;
	
	@Column(nullable = false)
	private int capacidad;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NombreTren getNombre() {
		return nombre;
	}

	public void setNombre(NombreTren nombre) {
		this.nombre = nombre;
	}

	public TipoTren getTipo() {
		return tipo;
	}

	public void setTipo(TipoTren tipo) {
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
}
