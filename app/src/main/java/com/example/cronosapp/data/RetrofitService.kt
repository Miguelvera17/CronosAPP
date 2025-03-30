package com.example.cronosapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import retrofit2.http.PATCH

interface RetrofitService {


    @GET("alumnos/")
    suspend fun listarAlumnos(): List<Alumno>

    @POST("alumnos/")
    suspend fun crearAlumno(@Body alumno: Alumno): Alumno

    @PATCH("alumnos/{nombre}")
    suspend fun editarAlumno(
        @Path("nombre") nombre: String,
        @Body cambios: Map<String, Any>
    ): Alumno

    @DELETE("alumnos/{nombre}")
    suspend fun eliminarAlumno(@Path("nombre") nombre: String)

    companion object {
        private const val BASE_URL = "https://100.27.115.52/"

        fun makeRetrofitService(): RetrofitService {
            val okHttpClient = OkHttpClient.Builder()
                .sslSocketFactory(createInsecureSSLSocketFactory(), createInsecureTrustManager())
                .hostnameVerifier { _, _ -> true }
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }

        private fun createInsecureSSLSocketFactory(): SSLSocketFactory {
            val trustAllCerts = arrayOf<TrustManager>(createInsecureTrustManager())
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            return sslContext.socketFactory
        }

        private fun createInsecureTrustManager(): X509TrustManager {
            return object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        }
    }
}