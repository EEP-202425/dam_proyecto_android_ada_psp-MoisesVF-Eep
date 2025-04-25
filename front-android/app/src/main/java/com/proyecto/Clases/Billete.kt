package com.proyecto.Clases

import kotlinx.serialization.Serializable


@Serializable
data class Billete(
    val id: Long? = null,
    val asiento: Int,
    val ruta: Rutas


)



{


}