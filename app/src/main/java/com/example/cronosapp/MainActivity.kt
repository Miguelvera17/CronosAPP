package com.example.cronosapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.ui.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias de la UI
        val emailEditText: EditText = findViewById(R.id.editTextEmail)
        val usernameEditText: EditText = findViewById(R.id.editTextUser)
        val passwordEditText: EditText = findViewById(R.id.editTextExample)
        val loginButton: Button = findViewById(R.id.loginButton)
        val messageTextView: TextView = findViewById(R.id.textViewMessage)

        // Acción de login al presionar el botón
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginViewModel.login(email, username, password)
        }

        // Observador del estado del login
        loginViewModel.loginStatus.observe(this) { status ->
            when (status) {
                is LoginViewModel.LoginStatus.Success -> {
                    messageTextView.text = "Login Successful"
                    messageTextView.visibility = View.VISIBLE
                }
                is LoginViewModel.LoginStatus.Error -> {
                    messageTextView.text = status.message
                    messageTextView.visibility = View.VISIBLE
                }
            }
        }
    }
}
