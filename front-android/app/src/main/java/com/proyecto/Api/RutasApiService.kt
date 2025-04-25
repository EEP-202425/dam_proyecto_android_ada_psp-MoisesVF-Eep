package com.proyecto.Api


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.proyecto.Clases.Billete
import com.proyecto.Clases.Pasajero
import com.proyecto.Clases.Rutas
import kotlinx.serialization.ExperimentalSerializationApi

import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val URLBASE = "http://10.0.2.2:4000/"

val json = Json { ignoreUnknownKeys = true }
@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .baseUrl(URLBASE)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()




interface ApiService {
@GET("ruta")
suspend fun getRuta(): List<Rutas>

@GET("ruta/{Origen}")
suspend fun getRutaByCiudad(@Path("Origen")id: String): List<Rutas>

@POST("pasajero")
suspend fun crearPasajero(@Body pasajero: Pasajero): Response <Pasajero>

@POST("billete/pasajero/{pasajeroId}")
suspend fun crearBillete(@Path("pasajeroId") id: Long?, @Body billete: Billete): Response <Billete>

@POST("pasajero/id")
suspend fun obtenerId(@Body pasajero: Pasajero?): Response<Long>
}

object RutasApi{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}






















