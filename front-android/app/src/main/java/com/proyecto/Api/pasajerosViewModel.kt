package com.proyecto.Api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.proyecto.Clases.Pasajero
import java.io.IOException

class pasajerosViewModel: ViewModel(){

    var personaSeleccionada by mutableStateOf<Pasajero?>(null)
    var pasajeroNuevo by mutableStateOf<Pasajero?>(null)

   suspend fun modificarPasajero(id: Long?, pasajero: Pasajero): Pasajero? {
        return try {
            val response = RutasApi.retrofitService.actualizarPasajero(id = id, pasajero = pasajero)
            if (response.isSuccessful) {
                val guardado = response.body()
                pasajeroNuevo = guardado
                guardado
            } else {
                Log.e("ERROR", "Error al guardar pasajero: ${response.message()}")
                null
            }
        } catch (e: IOException) {
            Log.e("ERROR", "Error de conexión: ${e.message}")
            null
        } catch (e: Exception) {
            Log.e("ERROR", "Error general: ${e.message}")
            null
        }
        return pasajeroNuevo
    }


}