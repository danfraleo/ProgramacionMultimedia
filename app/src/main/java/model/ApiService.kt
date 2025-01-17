package model
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Query
import usuario


interface ApiService {

    @GET("?action=usuario&{id}") // Ejemplo de endpoint
    fun getUser(@Path("id") userId: Int): Call<usuario> // Define el tipo de retorno


    @GET("?action=usuario") // Ejemplo de endpoint
    fun getUserS():  Call<List<usuario>> // Define el tipo de retorno

    @DELETE("?action=usuario") // Define la ruta sin la llave en la URL
    fun delUser(@Query("id") userId: Int): Call<Void> // Usa @Query para parámetros de consulta

    @POST("?action=usuario")
    fun addUser(
        @Body usuario: usuario
    ): Call<Void>

    // Actualizar un usuario existente (PUT)
    @PUT("?action=usuario")
    fun updateUser(
        @Query("id") userId: Int,
        @Body usuario: usuario
    ): Call<Void>


}