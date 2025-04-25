package com.proyecto.Screens

import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import com.proyecto.Api.RutasViewModel
import com.proyecto.Api.pasajerosViewModel
import com.proyecto.Clases.Billete
import com.proyecto.Clases.Rutas
import com.proyecto.R
import com.proyecto.navegacion.Routes
import com.proyecto.ui.theme.IndianRed
import com.proyecto.ui.theme.RojoProfundo
import com.proyecto.ui.theme.rosaPalo

@Composable
fun aceptarBillete(
    innerPadding: PaddingValues,
    navigationController: NavHostController,
    rutasViewModel: RutasViewModel,
    pasajerosViewModel: pasajerosViewModel
) {
        val ruta = rutasViewModel.rutaSeleccionada
        val persona = pasajerosViewModel.personaSeleccionada
        rutasViewModel.obtnerIdPasajero(persona)

    val id: Long? = rutasViewModel.idpasajeroGuardado.value


    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize().background(rosaPalo).padding(10.dp)) {
            Spacer(modifier = Modifier.size(75.dp))

            Button(onClick = {navigationController.navigate(Routes.Pantalla2.misRutas)},
                colors = ButtonDefaults.buttonColors(IndianRed),
                shape = RoundedCornerShape(10.dp)) {
                Text("Atrás")
            }
            Spacer(modifier = Modifier.size(15.dp))
            Box(modifier = Modifier.fillMaxWidth().height(325.dp).background(rosaPalo), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.ticket___free_music_icons),
                    contentDescription = "Fondo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.3f)
                        .clip(RoundedCornerShape(10.dp))
                )
                Row() {
                Text("Usuario: ${persona?.nombre}\n" +
                        "Apellido: ${persona?.apellido}\n" +
                        "mail: ${persona?.email}\n" +
                        "Telefono: ${persona?.telefono}"
                    , textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)

                Text("Origen: ${ruta?.id}\n" +
                        "Destino: ${ruta?.llegada}\n" +
                        "${ruta?.cogerPrimero()}\n"

                    , textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            Image(painter = painterResource(id = R.drawable.keyboard_double_arrow_down_24dp_000000_fill0_wght400_grad0_opsz24),
                contentDescription = "flecha", modifier = Modifier.size(50.dp))
            Image(painter = painterResource(id = R.drawable.descargar_icono_de_billete_de_tren_con_sombra_gratis),contentDescription = "Ícono de billete de tren"
            ,contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(16.dp)))
            Spacer(modifier = Modifier.size(15.dp))
            CrearBilleteButton(rutasViewModel, id, ruta)
        }





}
@Composable
fun CrearBilleteButton(viewModel: RutasViewModel, id: Long?, rutaId: Rutas?) {
    val randomAsiento = (1..100).random()

    Button(onClick = {
        if (id != null && rutaId != null) {

            Log.e("${id}","")
            val billete = Billete(
                asiento = randomAsiento,
                ruta = Rutas(id = rutaId.id,rutaId.origen,rutaId.llegada,rutaId.trenes)
            )

            viewModel.guardarBillete(id,billete)

        } else {
            Log.e("ERROR", "ID del pasajero es nulo")
        }
    },colors = ButtonDefaults.buttonColors(RojoProfundo),
        shape = RoundedCornerShape(10.dp)) {
        Text(text = "Crear Billete")

    }
}
