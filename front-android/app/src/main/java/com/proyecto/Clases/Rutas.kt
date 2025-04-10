package com.proyecto.Clases


import kotlinx.coroutines.launch

data class Rutas(
    val id:Long,
    val  Origen: Ciudades,
    val Destino: Ciudades,
    val trenes: List<Tren> = listOf()
    )

