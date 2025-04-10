package com.proyecto.Clases


import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch

data class Rutas(
    @SerializedName("id")
    val id:Long,

    @SerializedName("origen")
    val  Origen: Ciudades,

    @SerializedName("llegada")
    val Destino: Ciudades,

    @SerializedName("trenes")
    val trenes: List<Tren> = listOf()
    )

