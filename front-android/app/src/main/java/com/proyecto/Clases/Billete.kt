package com.proyecto.Clases

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable


@Serializable
data class Billete(
    val id: Long? = null,
    val asiento: Int,
    val ruta: Rutas


)
@Composable
fun getId(billete: Billete?): Long? {

    return billete?.id
}






