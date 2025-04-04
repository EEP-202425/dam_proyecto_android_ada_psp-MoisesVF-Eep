package com.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.proyecto.ui.theme.IndianRed
import com.proyecto.ui.theme.ProyectoTheme
import com.proyecto.ui.theme.RojoFondo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    cuerpoPagina(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun cuerpoPagina(modifier: Modifier = Modifier) {

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

        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,Alignment.CenterHorizontally) {
            val deliusFont = FontFamily(
                Font(R.font.delius_regular)
            )
            Text(text = stringResource(id =R.string.app_name), fontWeight = FontWeight.ExtraBold, fontSize = 32.sp,maxLines = 1,
                fontFamily = deliusFont,
                modifier = Modifier.padding(bottom = 105.dp), color = Color.White, textDecoration = TextDecoration.Underline)

            Column(verticalArrangement = Arrangement.spacedBy(25.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                var name =  cuadroNombre(modifier = Modifier.fillMaxWidth())
                var sur = cuadroApellido(modifier = Modifier.fillMaxWidth())
                var mail = cuadroEmail(modifier = Modifier.fillMaxWidth())
                var tel = cuadroTelf(modifier = Modifier.fillMaxWidth())
                var completado :Boolean = false
                if(name && sur && mail && tel){
                    completado = true

                }
                inicio(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),completado)

            }
        }
    }
}

@Composable
fun cuadroNombre(modifier: Modifier): Boolean {
    var nombre:String by rememberSaveable { mutableStateOf("") }

   TextField(nombre, onValueChange = {nombre = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_name)) },modifier=Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    if(nombre.isNotEmpty()){
        return true
    }else{
        return false;
    }

}

@Composable
fun cuadroApellido(modifier: Modifier): Boolean {
    var ape:String by rememberSaveable { mutableStateOf("") }
    TextField(ape, onValueChange = {ape = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_Ape)) },modifier=Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    if(ape.isNotEmpty()){
        return true
    }else{
        return false;
    }
}

@Composable
fun cuadroEmail(modifier: Modifier): Boolean {
    var mail:String by rememberSaveable { mutableStateOf("") }
    TextField(mail, onValueChange = {mail = it}, maxLines = 1,label = { Text(stringResource(id= R.string.label_mail))},modifier=Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    if(mail.isNotEmpty()){
        return true
    }else{
        return false;
    }
}

@Composable
fun cuadroTelf(modifier: Modifier): Boolean {
    var tel:String by rememberSaveable{ mutableStateOf("")}
    TextField(tel, onValueChange = {numero ->
        if(numero.length <= 9){
             tel=numero }
        }
        , maxLines = 1,label = { Text(stringResource(id= R.string.label_telf)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),modifier=Modifier.alpha(0.8f), shape = RoundedCornerShape(10.dp))
    if(tel.isNotEmpty()){
        return true
    }else{
        return false;
    }
}

@Composable
fun inicio(modifier: Modifier,completado:Boolean){
    Button(onClick = {}, colors = ButtonDefaults.buttonColors(IndianRed), enabled = completado, shape = RoundedCornerShape(10.dp) ) {
        Text(stringResource(id=R.string.entrar), fontWeight = FontWeight.Bold)
    }
}
