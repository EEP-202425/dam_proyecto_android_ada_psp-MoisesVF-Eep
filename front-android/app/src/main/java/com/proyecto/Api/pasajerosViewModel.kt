package com.proyecto.Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyecto.Clases.Pasajero

class pasajerosViewModel: ViewModel(){

    var personaSeleccionada by mutableStateOf<Pasajero?>(null)
}