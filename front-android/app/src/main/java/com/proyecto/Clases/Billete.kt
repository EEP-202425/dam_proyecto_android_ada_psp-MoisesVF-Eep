package com.proyecto.Clases

import kotlinx.serialization.Serializable


@Serializable
data class Billete(

    val asiento: Int,
    val pasajero_id: Long,
    val ruta_id: Long


) {


}