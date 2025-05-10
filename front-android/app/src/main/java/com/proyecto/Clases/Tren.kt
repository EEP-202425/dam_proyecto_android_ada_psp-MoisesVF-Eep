package com.proyecto.Clases

import kotlinx.serialization.Serializable

@Serializable
data class Tren(
    val id: Long,
    val nombre: NombreTren,
    val tipo: TipoTren,
    val capacidad: Int)
    {

    override fun toString(): String {
        return "Tren: nombre=$nombre\n" +
                "tipo=$tipo\n" +
                "capacidad=$capacidad"
    }

}
