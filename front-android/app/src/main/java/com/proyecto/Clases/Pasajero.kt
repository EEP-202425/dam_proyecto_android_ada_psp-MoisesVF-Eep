package com.proyecto.Clases

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class Pasajero (


    val nombre:String
    , val apellido:String
    , val email: String
    , val telefono : String
    , val billetes: List<Billete> = emptyList())

    @Composable
    fun getName(pasajero: Pasajero):String{
        return pasajero.nombre
    }
    @Composable
    fun getApe(pasajero: Pasajero):String{
        return pasajero.apellido
    }
    @Composable
    fun getMail(pasajero: Pasajero):String{
        return pasajero.email
    }
    @Composable
    fun getTelef(pasajero: Pasajero):String{
        return pasajero.telefono
    }

