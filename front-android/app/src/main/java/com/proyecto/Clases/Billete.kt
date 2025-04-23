package com.proyecto.Clases

import kotlinx.serialization.Serializable


@Serializable
data class Billete(
    val id: Long? = null,
    val asiento: Int,
    val ruta_id: Long,
    val pasajero_id: Long

) {

    override fun toString(): String {
        return "Billete con id: " + id +"\n" +
                "asiento: " + asiento
    }

}