package com.example.flowtogether

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import model.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import usuario

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loguear(view: View) {
        val username = findViewById<EditText>(R.id.idUser).text.toString()
        val password = findViewById<EditText>(R.id.passUser).text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            verificarCredenciales(username, password)
        } else {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verificarCredenciales(username: String, password: String) {
        // Llamada a la API para obtener todos los usuarios
        val call = RetrofitInstance.apiService.getUserS()

        // Retrofit maneja el hilo secundario con enqueue
        call.enqueue(object : Callback<List<usuario>> {
            override fun onResponse(call: Call<List<usuario>>, response: Response<List<usuario>>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    val usuarioValido = usuarios?.find { it.email == username && it.contrasena == password }

                    if (usuarioValido != null) {
                        runOnUiThread {
                            val intent = Intent(this@LoginActivity, MainActivity2::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Error al verificar credenciales", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<usuario>>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e("Error de conexión", t.message ?: "Sin mensaje de error")
            }
        })
    }

}
