package com.proyecto.proyecto.tablas;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "ruta_id", nullable = false)
    @JsonBackReference
    private Rutas ruta;

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

	public Rutas getRuta() {
		return ruta;
	}

	public void setRuta(Rutas ruta) {
		this.ruta = ruta;
	}
	

}
