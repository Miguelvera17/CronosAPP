package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResumenAsistenciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_asistencia)
        val imgButtonBack :ImageButton = findViewById(R.id.imageButtonBack)
        val buttonDetalle : Button = findViewById(R.id.buttonDetail)

        imgButtonBack.setOnClickListener {
            val intent = Intent(this, FichajeAlumnoActivity::class.java)
            startActivity(intent)
        }
        buttonDetalle.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        val spinner: Spinner = findViewById(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.time_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = adapter
    }
}