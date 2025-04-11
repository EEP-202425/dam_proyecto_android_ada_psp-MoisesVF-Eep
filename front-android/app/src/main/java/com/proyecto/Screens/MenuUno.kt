package com.proyecto.Screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.proyecto.Api.RutasViewModel



@Composable
fun menuInicio(modifier: Modifier= Modifier,rutasViewModel: RutasViewModel) {

    val rutas = rutasViewModel.rutas.value
    val error = rutasViewModel.error.value
    if (error != null) {
        Text("Error: $error", color = Color.Red, modifier = modifier)
    }


    if (rutas.isNotEmpty()) {


        Column(modifier = modifier) {
            rutas.forEach { ruta ->
                val origen = ruta.origen.name
                val destino = ruta.llegada.name
                Text(
                    text = "Origen: $origen - Destino: $destino",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    } else {

        Text(text = "No hay rutas disponibles.", modifier = modifier)

        Box() {
            Column() {

            }
        }
    }
}


