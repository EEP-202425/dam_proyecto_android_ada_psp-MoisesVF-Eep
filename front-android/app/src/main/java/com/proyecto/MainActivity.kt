package com.proyecto


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

import com.proyecto.Api.RutasViewModel
import com.proyecto.Api.pasajerosViewModel
import com.proyecto.Screens.aceptarBillete
import com.proyecto.Screens.cuerpoPagina
import com.proyecto.Screens.menuInicio

import com.proyecto.navegacion.Routes

import com.proyecto.ui.theme.ProyectoTheme


class MainActivity : ComponentActivity() {
    private val rutasViewModel: RutasViewModel by viewModels()
    private val pasajerosViewModel: pasajerosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rutasViewModel.obtenerRutas()

        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Pantalla1.misRutas){
                        composable(Routes.Pantalla1.misRutas) {cuerpoPagina(Modifier.padding(innerPadding),rutasViewModel = rutasViewModel,navigationController, pasajerosViewModel) }
                        composable(Routes.Pantalla2.misRutas) {menuInicio(Modifier.padding(innerPadding),rutasViewModel = rutasViewModel,navigationController, pasajerosViewModel) }
                        composable("billetes") { aceptarBillete(innerPadding,navigationController,rutasViewModel,pasajerosViewModel) }
                    }




                }
            }
        }
    }
}

