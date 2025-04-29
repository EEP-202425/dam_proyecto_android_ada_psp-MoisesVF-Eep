package com.proyecto.Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.Clases.Billete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BilleteViewModel : ViewModel() {
    var billeteEscogido by mutableStateOf<Billete?>(null)

    val eliminado = MutableStateFlow<String?>(null)

    fun deletearBillete(id: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resultado = RutasApi.retrofitService.borrarBillete(id)
                eliminado.value = resultado.body() ?: "Eliminado con éxito"
            } catch (e: IOException) {
                eliminado.value = "No se eliminó (error de red)"
            } catch (e: HttpException) {
                eliminado.value = "No se pudo eliminar (error del servidor)"
            } catch (e: Exception) {
                eliminado.value = "No se pudo eliminar"
            }
        }
    }

}