package com.proyecto.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyecto.Api.RutasViewModel
import com.proyecto.Clases.Pasajero
import com.proyecto.R
import com.proyecto.ui.theme.IndianRed
import com.proyecto.ui.theme.RojoFondo

@Composable
fun cuerpoPagina(modifier: Modifier = Modifier,rutasViewModel: RutasViewModel) {

    var name by remember { mutableStateOf("") }
    var surNa by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var tel by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(RojoFondo)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_0928),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f) // Ajusta la opacidad
        )

        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally) {
            val deliusFont = FontFamily(
                Font(R.font.delius_regular)
            )
            Text(text = stringResource(id = R.string.app_name), fontWeight = FontWeight.ExtraBold, fontSize = 32.sp,maxLines = 1,
                fontFamily = deliusFont,
                modifier = Modifier.padding(bottom = 105.dp), color = Color.White, textDecoration = TextDecoration.Underline)

            Column(verticalArrangement = Arrangement.spacedBy(25.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                 name =  cuadroNombre(modifier = Modifier.fillMaxWidth())
                 surNa = cuadroApellido(modifier = Modifier.fillMaxWidth())
                 mail = cuadroEmail(modifier = Modifier.fillMaxWidth())
                 tel = cuadroTelf(modifier = Modifier.fillMaxWidth())
                var completado :Boolean = false
                if(name.length > 0 && surNa.length > 0 && mail.length > 0 && tel.length > 0){
                    completado = true

                }

                inicio(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),completado,name,surNa,mail,tel,rutasViewModel)



            }
        }
    }
}

@Composable
fun cuadroNombre(modifier: Modifier): String {
    var nombre:String by rememberSaveable { mutableStateOf("") }

    TextField(nombre, onValueChange = {nombre = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_name)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return nombre ;

}

@Composable
fun cuadroApellido(modifier: Modifier): String {
    var ape:String by rememberSaveable { mutableStateOf("") }
    TextField(ape, onValueChange = {ape = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_Ape)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return ape;
}

@Composable
fun cuadroEmail(modifier: Modifier): String {
    var mail:String by rememberSaveable { mutableStateOf("") }
    TextField(mail, onValueChange = {mail = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_mail)) },modifier= Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    return mail;

}

@Composable
fun cuadroTelf(modifier: Modifier): String {
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


@Composable
fun inicio(
    modifier: Modifier,
    completado: Boolean,
    name: String,
    surNa: String,
    mail: String,
    tel: String,
    rutasViewModel: RutasViewModel
) {

    Button(
        onClick = {

            if (completado) {
                val usuario = Pasajero(name, surNa, mail, tel)
                rutasViewModel.guardarPasajero(usuario)
            }
        },
        colors = ButtonDefaults.buttonColors(IndianRed),
        enabled = completado,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(stringResource(id = R.string.entrar), fontWeight = FontWeight.Bold)
    }
}


