package com.proyecto.Clases

import kotlinx.serialization.Serializable

@Serializable
data class Rutas(
    val id:Long,
    val origen: Ciudades,
    val llegada: Ciudades,
    val trenes: List<Tren>? = null
    ){
    fun cogerPrimero(): Tren? {
        trenes?.isEmpty()?.let {
            if (!it) {
                return trenes?.get(0)
            }
        }
        return null
    }
}

