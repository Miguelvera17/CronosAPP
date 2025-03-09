package com.example.cronosapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {

    @GET("alumnos/")
    suspend fun listarAlumnos(): List<Alumno>

    @POST("alumnos/")
    suspend fun crearAlumno(@Body alumno: Alumno): Alumno

    @DELETE("alumnos/{nombre}")
    suspend fun eliminarAlumno(@Path("nombre") nombre: String)

    companion object {
        private const val BASE_URL = "https://100.27.115.52/"

        fun makeRetrofitService(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}