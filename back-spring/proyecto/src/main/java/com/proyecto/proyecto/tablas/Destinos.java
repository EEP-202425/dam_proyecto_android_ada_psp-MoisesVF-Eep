package com.proyecto.proyecto.tablas;

public enum Destinos {
	Madrid("madrid"), Barcelona("barcelona"), Valencia("valencia"),
	Sevilla("sevilla"), Bilbao("bilbao"), Málaga("malaga"), Zaragoza("zaragoza"), 
	Granada("granada"), Alicante("alicante"), Salamanca("salamanca"), Córdoba("cordoba"), 
	Toledo("toledo");

	
	private String ciudad;
	
	Destinos(String ciudad) {
		this.ciudad=ciudad;
		
		
		
	}

	public String getCiudad() {
		return ciudad;
	}

	
	


}

