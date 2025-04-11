package com.proyecto.Api


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.proyecto.Clases.Rutas

import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

import retrofit2.http.GET
import retrofit2.http.Path

private const val URLBASE = "http://10.0.2.2:4000/"

val json = Json { ignoreUnknownKeys = true }
private val retrofit = Retrofit.Builder()
    .baseUrl(URLBASE)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()




interface ApiService {
@GET("ruta")
suspend fun getRuta(): List<Rutas>

@GET("ruta/{id}")
suspend fun getRutaById(@Path("id")id: String): Rutas

}

object RutasApi{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}






















