package model // Ajusta al paquete adecuado

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base URL de tu API
    private const val BASE_URL = "http://10.0.2.2/PHPulse/" // Reemplaza con tu URL

    // Instancia de Retrofit
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para JSON
            .build()
    }

    // Servicio para la API
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
