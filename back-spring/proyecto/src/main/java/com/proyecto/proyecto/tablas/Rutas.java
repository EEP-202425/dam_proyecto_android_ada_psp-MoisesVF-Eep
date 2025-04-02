package com.proyecto.proyecto.tablas;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rutas")
public class Rutas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "Origen", nullable = false)
	private Destinos origen;

	@Enumerated(EnumType.STRING)
	@Column(name = "Destino", nullable = false)
	private Destinos llegada;

	@OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tren> trenes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Destinos getOrigen() {
		return origen;
	}

	public void setOrigen(Destinos origen) {
		this.origen = origen;
	}

	public Destinos getLlegada() {
		return llegada;
	}

	public void setLlegada(Destinos llegada) {
		this.llegada = llegada;
	}

	public List<Tren> getTrenes() {
		return trenes;
	}

	public void setTrenes(List<Tren> trenes) {
		this.trenes = trenes;
	}

}
