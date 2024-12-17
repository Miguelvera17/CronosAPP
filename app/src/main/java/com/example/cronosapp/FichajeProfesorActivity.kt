package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FichajeProfesorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fichaje_profesor)

        val bttonFichaje : Button = findViewById(R.id.button2)
        bttonFichaje.setOnClickListener {
            Toast.makeText(this, "Entrada registrada", Toast.LENGTH_SHORT).show()
        }

        val bttonFichaje2 : Button = findViewById(R.id.button3)
        bttonFichaje2.setOnClickListener {
            Toast.makeText(this, "Salida registrada", Toast.LENGTH_SHORT).show()
        }
    }
}