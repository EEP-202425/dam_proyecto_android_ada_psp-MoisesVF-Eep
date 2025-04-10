package com.proyecto.Api

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

import androidx.lifecycle.lifecycleScope
import com.proyecto.Clases.Rutas
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val URLBASE = "http://10.0.2.2:4000/"

interface ApiService {
@GET("ruta")
suspend fun getRuta(): List<Rutas>
}
class ServiceApi : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")

    @Composable
    fun RecogerRutas() {

        fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                val retrofit = Retrofit.Builder()
                    .baseUrl(URLBASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                val service = retrofit.create(ApiService::class.java)
                lifecycleScope.launch {
                    val response = service.getRuta()
                    response.forEach {
                        println(it)
                    }
                }
            }
        }
    }
}


















