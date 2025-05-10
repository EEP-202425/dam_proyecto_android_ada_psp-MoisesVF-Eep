package com.proyecto.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.proyecto.Api.RutasViewModel
import com.proyecto.Api.pasajerosViewModel
import com.proyecto.Clases.Pasajero
import com.proyecto.Clases.Rutas
import com.proyecto.R
import com.proyecto.navegacion.Routes
import com.proyecto.ui.theme.Blancofondo
import com.proyecto.ui.theme.IndianRed
import com.proyecto.ui.theme.RojoProfundo
import com.proyecto.ui.theme.rosaPalo


@Composable
fun menuInicio(
    modifier: Modifier = Modifier,
    rutasViewModel: RutasViewModel,
    navigationController: NavHostController,
    pasajerosViewModel: pasajerosViewModel
) {

    val deliusFont = FontFamily(
        Font(R.font.delius_regular)
    )

    val persona = pasajerosViewModel.personaSeleccionada

    var routes by remember { mutableStateOf<List<Rutas>>(emptyList()) }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Blancofondo), verticalArrangement = Arrangement.Center) {
            Row(modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(RojoProfundo),Arrangement.Center) {

                Text("Usuario: ${persona?.nombre}\n" +
                        "Apellido: ${persona?.apellido}\n",
                    fontFamily = deliusFont,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(12.dp)).background(Blancofondo).
                    clickable(onClick = {navigationController.navigate(Routes.Pantalla4.misRutas)})
                    )

                Image(

                    painter = painterResource(id = R.drawable.img_0928),
                    contentDescription = "Fondo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f)

                )



            }
            Row (modifier= Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {navigationController.navigate(Routes.Pantalla1.misRutas)},
                    colors = ButtonDefaults.buttonColors(rosaPalo),
                    shape = RoundedCornerShape(10.dp) ) {

                    Text(
                        text = "Atrás",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center

                    )

                }

                var ciudadDePartida by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = ciudadDePartida,
                    onValueChange = { ciudadDePartida = it },
                    label = { Text("Ciudad de Partida") },
                    modifier = Modifier.width(170.dp)
                )

                Button(onClick = {
                   routes = rutasViewModel.buscarOrigen(ciudadDePartida)


                },colors = ButtonDefaults.buttonColors(RojoProfundo),
                    shape = RoundedCornerShape(10.dp) ) {
                    Text(
                        text = "Buscar",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }

            }
            buscadorFinal(routes,navigationController,rutasViewModel)
            Lugares(rutasViewModel = rutasViewModel,navigationController)

        }

}


@Composable
fun buscadorFinal(routes: List<Rutas>, navigationController: NavHostController,rutasViewModel: RutasViewModel) {
    if (routes.isNotEmpty()) {

        Column(modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(RojoProfundo)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center) {

            routes.forEach { ruta ->
                val origen = ruta.origen.name
                val destino = ruta.llegada.name
                val trenes = ruta.trenes?.size
                Box(
                    modifier = Modifier
                        .clickable(onClick = {
                            rutasViewModel.rutaSeleccionada = ruta
                            navigationController.navigate(Routes.Pantalla3.misRutas)})
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Blancofondo),

                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Origen: $origen - Destino: $destino - Trenes: $trenes",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Red,


                        )

                }
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }else{


            Text(
                text = "No se buscaron rutas.",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
}




@Composable
fun Lugares (rutasViewModel: RutasViewModel, navigationController: NavHostController){
    val rutas = rutasViewModel.rutas.value
    val error = rutasViewModel.error.value
    if (error != null) {
        Text("Error: $error", color = Color.Red, modifier = Modifier)
    }


    if (rutas.isNotEmpty()) {


        Column(modifier = Modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())) {

            rutas.forEach { ruta ->
                val origen = ruta.origen.name
                val destino = ruta.llegada.name
                val trenes = ruta.trenes?.size
                Box(
                    modifier = Modifier
                        .clickable(onClick = {
                            rutasViewModel.rutaSeleccionada = ruta
                            navigationController.navigate(Routes.Pantalla3.misRutas)})
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(IndianRed),

                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Origen: $origen - Destino: $destino - Trenes: $trenes",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,


                    )

                }
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    } else {

        Text(text = "No hay rutas disponibles.", modifier = Modifier)

        Box() {
            Column() {

            }
        }
    }
}


