package com.proyecto.Clases

import androidx.compose.runtime.Composable


data class Pasajero (


     val nombre:String
    ,val apellido:String
    ,val email: String
    ,val telefono : String ){

    @Composable
    fun getName():String{
        return this.nombre
    }
    @Composable
    fun getApe():String{
        return this.apellido
    }
    @Composable
    fun getMail():String{
        return this.email
    }
    @Composable
    fun getTelef():String{
        return this.telefono
    }

}