package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AsistenciaClaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_asistencia_clase)

        val bttonBack : ImageButton = findViewById(R.id.imgButtonBack)
        bttonBack.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
        }

        val bttonSave : Button = findViewById(R.id.buttonSave)
        bttonSave.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Asistencia guardada", Toast.LENGTH_SHORT).show()
        }
    }
}