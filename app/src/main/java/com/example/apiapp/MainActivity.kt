package com.example.apiapp

import APIClient
import APIService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var editEmail: EditText
    private lateinit var editContrasena: EditText
    private lateinit var textHeader: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btn_login)
        editEmail = findViewById(R.id.et_correo)
        editContrasena = findViewById(R.id.et_contrasena)
        textHeader = findViewById(R.id.tv_header)

        btnLogin.setOnClickListener {
            val apiService = APIClient.getClient()
            val body: LoginRequest =
                LoginRequest(editEmail.text.toString(), editContrasena.text.toString())

            val call = apiService.login(body)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        // Manejar respuesta exitosa de la API
                        // loginResponse?.token de aquí podemos recuperar el token
                        textHeader.text = "Bienvenido ${loginResponse?.name}"
                        Toast.makeText(
                            applicationContext,
                            "Respuesta exitosa de la API",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    } else {
                        // Manejar respuesta de error de la API
                        Toast.makeText(
                            applicationContext,
                            "Respuesta de error de la API",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Manejar error de red o error en la llamada a la API
                    Toast.makeText(
                        applicationContext,
                        "Error en la llamada a la API",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}
