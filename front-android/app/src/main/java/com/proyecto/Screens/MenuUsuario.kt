package com.proyecto.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.proyecto.Api.BilleteViewModel
import com.proyecto.Api.RutasViewModel
import com.proyecto.Api.pasajerosViewModel
import com.proyecto.Clases.Billete
import com.proyecto.Clases.Pasajero
import com.proyecto.Clases.Rutas
import com.proyecto.R
import com.proyecto.navegacion.Routes
import com.proyecto.ui.theme.Blancofondo
import com.proyecto.ui.theme.IndianRed
import com.proyecto.ui.theme.RojoFondo
import com.proyecto.ui.theme.RojoProfundo
import com.proyecto.ui.theme.rosaPalo

@Composable
fun menuUusario(
    innerPadding: PaddingValues,
    navigationController: NavHostController,
    rutasViewModel: RutasViewModel,
    pasajerosViewModel: pasajerosViewModel,
    billeteViewModel: BilleteViewModel


) {
    var cambio by remember { mutableStateOf(false) }
    var modificado by remember { mutableStateOf(false) }
    val persona = pasajerosViewModel.personaSeleccionada
    val billete = billeteViewModel.billeteEscogido
    val idBilletes : Billete? = rutasViewModel.billeteGuardado.value


    Column(modifier = Modifier.background(color = IndianRed).fillMaxSize().padding(25.dp)) {
        Spacer(modifier = Modifier.size(15.dp))
        Box(contentAlignment = Alignment.Center,modifier = Modifier.weight(1f).fillMaxSize().clip(RoundedCornerShape(12.dp)).background(Blancofondo)) {
            Image(

                painter = painterResource(id = R.drawable.img_0928),
                contentDescription = "Fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.2f)

            )

               modificacionPasajero(modificado,persona,rutasViewModel,pasajerosViewModel,navigationController)


        }
        Spacer(modifier = Modifier.size(1.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {navigationController.navigate(Routes.Pantalla2.misRutas)}
                ,colors = ButtonDefaults.buttonColors(rosaPalo),
                shape = RoundedCornerShape(10.dp) ) {
                Text(
                    text = "Atras",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )}

            Button(onClick = {
                modificado=true},colors = ButtonDefaults.buttonColors(RojoProfundo),
                shape = RoundedCornerShape(10.dp) ) {
                Text(
                    text = "Modificar",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )}

               Button(
                    onClick = {billeteViewModel.deletearBillete(idBilletes?.id)
                        cambio = true

                              }
                    , colors = ButtonDefaults.buttonColors(RojoFondo),
                    shape = RoundedCornerShape(10.dp))
                { Text(
                        text = "Eliminar",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )}
                }


        Box(contentAlignment = Alignment.Center,modifier = Modifier.weight(1f).fillMaxSize().clip(RoundedCornerShape(12.dp)).background(Blancofondo)) {
            Image(

                painter = painterResource(id = R.drawable.ticket___free_music_icons),
                contentDescription = "Fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.2f)

            )

                cambioDeMensaje(cambio,billete)


        }



    }




}
@Composable
fun cambioDeMensaje(noBorrado: Boolean, billete: Billete?,){
    if(noBorrado==false){
        Text(

            "Billete:\n" +
                    "Origen: ${billete?.ruta?.origen}\n" +
                    "Llegada: ${billete?.ruta?.llegada}\n" +
                    "${billete?.ruta?.trenes}\n" +
                    "asiento: ${billete?.asiento}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )

    } else {
        Text(
            "El billete fue borrado",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun modificacionPasajero(
    cambiado: Boolean,
    persona: Pasajero?,
    rutasViewModel: RutasViewModel,
    pasajerosViewModel: pasajerosViewModel,
    navigationController: NavHostController

    ){
    if(cambiado==false) {
        Text(
            "Usuario: ${persona?.nombre}\n" +
                    "Apellido: ${persona?.apellido}\n" +
                    "mail: ${persona?.email}\n" +
                    "Telefono: ${persona?.telefono}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }else{
        var modificacionFinal by remember { mutableStateOf(false) }
        var nuevoName by remember { mutableStateOf("") }
        var nuevoSurNa by remember { mutableStateOf("") }
        var nuevoMail by remember { mutableStateOf("") }
        var nuevoTelf by remember { mutableStateOf("") }
        Column() {
            nuevoName =  cuadrNombre(modifier = Modifier.fillMaxWidth())
            nuevoSurNa = cuadrApellido(modifier = Modifier.fillMaxWidth())
            nuevoMail = cuadrEmail(modifier = Modifier.fillMaxWidth())
            nuevoTelf = cuadrTelf(modifier = Modifier.fillMaxWidth())

            val usuario = Pasajero(
                nombre = nuevoName,
                apellido = nuevoSurNa,
                email = nuevoMail,
                telefono = nuevoTelf
            )


            modificacionFinal=true
          if(modificacionFinal){
              Text(
                  "Usuario: ${persona?.nombre}\n" +
                          "Apellido: ${persona?.apellido}\n" +
                          "mail: ${persona?.email}\n" +
                          "Telefono: ${persona?.telefono}",
                  textAlign = TextAlign.Center,
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 20.sp)

              Text("aceptar", fontWeight = FontWeight.Bold ,modifier = Modifier.clickable{
                  rutasViewModel.guardarPasajero(usuario)
                  pasajerosViewModel.personaSeleccionada = usuario
                  navigationController.navigate(Routes.Pantalla2.misRutas)
              }
                  .background(IndianRed)
                  .padding(5.dp))


          }
        }


    }



}

@Composable
fun cuadrNombre(modifier: Modifier): String {
    var nombre:String by rememberSaveable { mutableStateOf("") }

    TextField(nombre, onValueChange = {nombre = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_name)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return nombre ;

}

@Composable
fun cuadrApellido(modifier: Modifier): String {
    var ape:String by rememberSaveable { mutableStateOf("") }
    TextField(ape, onValueChange = {ape = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_Ape)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return ape;
}

@Composable
fun cuadrEmail(modifier: Modifier): String {
    var mail:String by rememberSaveable { mutableStateOf("") }
    TextField(mail, onValueChange = {mail = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_mail)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return mail;

}

@Composable
fun cuadrTelf(modifier: Modifier): String {
    var tel:String by rememberSaveable{ mutableStateOf("") }
    TextField(tel, onValueChange = {numero ->
        if(numero.length <= 9){
            tel=numero }
    }
        , maxLines = 1,label = { Text(stringResource(id= R.string.label_telf)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp)
    )

    return tel;

}