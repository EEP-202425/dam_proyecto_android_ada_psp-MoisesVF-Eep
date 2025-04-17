package com.proyecto.Clases

import kotlinx.serialization.Serializable

@Serializable
data class Tren(
    val id: Long,
    val nombre: NombreTren,
    val tipo: TipoTren,
    val capacidad: Int) {

}
