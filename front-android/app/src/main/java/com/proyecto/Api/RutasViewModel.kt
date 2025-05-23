package com.proyecto.Api
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.Clases.Billete
import com.proyecto.Clases.Pasajero
import com.proyecto.Clases.Rutas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RutasViewModel: ViewModel() {
//-------------------------------------------------------------------------------------------------

    var rutaSeleccionada by mutableStateOf<Rutas?>(null)
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
//-----------------------------------------------------------------------------------
    val ciudad = mutableStateOf<List<Rutas>>(emptyList())


    fun buscarOrigen(lugar: String): List<Rutas> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resultado = RutasApi.retrofitService.getRutaByCiudad(lugar)

                ciudad.value = resultado

            } catch (e: IOException) {

                ciudad.value = emptyList()
            } catch (e: HttpException) {

                ciudad.value = emptyList()
            } catch (e: Exception) {

                ciudad.value = emptyList()
            }
        }
        return ciudad.value
    }

    //-----------------------------------------------------------------------
    val pasajeroGuardado = mutableStateOf<Pasajero?>(null)

    fun guardarPasajero(pasajero: Pasajero): Pasajero? {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = RutasApi.retrofitService.crearPasajero(pasajero)


                if (response.isSuccessful) {

                    val guardado = response.body()

                    if (guardado != null) {
                        pasajeroGuardado.value = guardado
                    } else {

                        Log.e("ERROR", "El cuerpo de la respuesta es nulo")
                    }
                } else {

                    Log.e("ERROR", "Error al guardar pasajero: ${response.message()}")
                }
            } catch (e: IOException) {

                Log.e("ERROR", "Error de conexión: ${e.message}")
            } catch (e: Exception) {

                Log.e("ERROR", "Error general: ${e.message}")
            }
        }

        return pasajeroGuardado.value
    }
    //-----------------------------------------------------------------------------
    val billeteGuardado = mutableStateOf<Billete?>(null)

    fun guardarBillete(id: Long?, billete: Billete) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                Log.d("GuardarBillete", "Billete: $billete")
                Log.d("GuardarBillete", "PasajeroId: $id")


                val response = RutasApi.retrofitService.crearBillete(id, billete)

                if (response.isSuccessful) {
                    val guardado = response.body()
                    if (guardado != null) {
                        billeteGuardado.value = guardado

                        Log.d("BilleteGuardado", "Billete guardado exitosamente: $guardado")
                    } else {
                        Log.e("ERROR", "El cuerpo de la respuesta es nulo")
                    }
                } else {
                    Log.e("ERROR", "Error al guardar billete: ${response.message()}")
                }
            } catch (e: IOException) {
                Log.e("ERROR", "Error de conexión: ${e.message}")
            } catch (e: Exception) {
                Log.e("ERROR", "Error general: ${e.message}")
            }
        }
    }


    val idpasajeroGuardado = mutableStateOf<Long?>(null)

    fun obtnerIdPasajero(pasajero: Pasajero?): Long? {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = RutasApi.retrofitService.obtenerId(pasajero)


                if (response.isSuccessful) {

                    val guardado = response.body()

                    if (guardado != null) {
                        idpasajeroGuardado.value = guardado
                    } else {

                        Log.e("ERROR", "El cuerpo de la respuesta es nulo")
                    }
                } else {

                    Log.e("ERROR", "Error al obtener id pasajero: ${response.message()}")
                }
            } catch (e: IOException) {

                Log.e("ERROR", "Error de conexión: ${e.message}")
            } catch (e: Exception) {

                Log.e("ERROR", "Error general: ${e.message}")
            }
        }

        return idpasajeroGuardado.value
    }



    }
