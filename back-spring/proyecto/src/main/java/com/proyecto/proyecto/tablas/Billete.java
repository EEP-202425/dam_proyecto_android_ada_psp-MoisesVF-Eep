package com.proyecto.proyecto.tablas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Billete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int asiento;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pasajero_id", nullable = false)
	private Pasajero pasajero;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ruta_id", nullable = false)
	private Rutas ruta;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public int getAsiento() {
		return asiento;
	}



	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}



	public Pasajero getPasajero() {
		return pasajero;
	}



	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}



	public Rutas getRuta() {
		return ruta;
	}



	public void setRuta(Rutas ruta) {
		this.ruta = ruta;
	}
	
	

}
