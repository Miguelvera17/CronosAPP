package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
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
            val intent = Intent(this, FichajeProfesorActivity::class.java)
            startActivity(intent)
        }

    }
}