package com.proyecto.Api
import androidx.compose.runtime.State

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.Clases.Rutas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RutasViewModel: ViewModel() {

    private val _rutas = mutableStateOf<List<Rutas>>(emptyList())
    val rutas: State<List<Rutas>> get() = _rutas

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun obtenerRutas() {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val result = RutasApi.retrofitService.getRuta()

                _rutas.value = result

                _error.value = null
            } catch (e: IOException) {

                _error.value = "Error de red: ${e.message}"
                _rutas.value = emptyList()
            } catch (e: HttpException) {

                _error.value = "Error en la respuesta de la API: ${e.message}"
                _rutas.value = emptyList()
            } catch (e: Exception) {

                _error.value = "Error desconocido: ${e.message}"
                _rutas.value = emptyList()
            }
        }
    }
}