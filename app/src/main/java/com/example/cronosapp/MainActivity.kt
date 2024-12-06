package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.loginButton)
        button.setOnClickListener {
            val intent = Intent(this, FichajeProfesorActivity::class.java)
            startActivity(intent)
        }

        // Obtener referencia al Spinner
        val spinner: Spinner = findViewById(R.id.mySpinner)

        // Crear un ArrayAdapter usando el array de cadenas y un diseño de spinner predeterminado
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.language_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Especificar el diseño que se usará cuando aparezcan las opciones desplegables
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Aplicar el adaptador al spinner
        spinner.adapter = adapter
    }
}