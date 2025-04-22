package com.example.cronosapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.ui.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels() // Instancia del ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias de la UI
        val usernameEditText: EditText = findViewById(R.id.editTextUser)
        val passwordEditText: EditText = findViewById(R.id.editTextExample)
        val loginButton: Button = findViewById(R.id.loginButton)

        // Acción de login al presionar el botón
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginViewModel.login(username, password)
        }

        // Observar el estado de login para actualizar la UI
        loginViewModel.loginStatus.observe(this, { status ->
            when (status) {
                is LoginViewModel.LoginStatus.Success -> {
                    // Login exitoso
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    // Navegar a otra actividad o hacer lo que necesites
                }
                is LoginViewModel.LoginStatus.Error -> {
                    // Mostrar error
                    Toast.makeText(this, status.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
