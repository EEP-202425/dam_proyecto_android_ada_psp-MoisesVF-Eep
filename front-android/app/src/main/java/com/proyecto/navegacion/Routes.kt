package com.proyecto.navegacion

sealed class Routes (val misRutas : String){
    object  Pantalla1: Routes("login")
    object  Pantalla2: Routes("menuInicio")
    object  Pantalla3: Routes("menuBilletes")
    object Pantalla4: Routes("menuUsauario")
}