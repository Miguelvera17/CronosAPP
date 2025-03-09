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
            // Configurar OkHttpClient para deshabilitar la verificación SSL
            val okHttpClient = OkHttpClient.Builder()
                .sslSocketFactory(createInsecureSSLSocketFactory(), createInsecureTrustManager())
                .hostnameVerifier { _, _ -> true } // Ignorar la verificación del hostname
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient) // Asignar el OkHttpClient personalizado
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }

        // Crear un SSLSocketFactory que ignore la verificación SSL
        private fun createInsecureSSLSocketFactory(): SSLSocketFactory {
            val trustAllCerts = arrayOf<TrustManager>(createInsecureTrustManager())
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            return sslContext.socketFactory
        }

        // Crear un TrustManager que confíe en todos los certificados
        private fun createInsecureTrustManager(): X509TrustManager {
            return object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        }
    }
}