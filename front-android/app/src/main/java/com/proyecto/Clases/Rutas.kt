package com.proyecto.Clases

import kotlinx.serialization.Serializable

@Serializable
data class Rutas(
    val id:Long,
    val origen: Ciudades,
    val llegada: Ciudades,
    val trenes: List<Tren> = listOf()
    )

