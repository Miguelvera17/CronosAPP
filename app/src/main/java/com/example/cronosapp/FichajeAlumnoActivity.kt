package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FichajeAlumnoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fichaje_alumno)

        val imgBack = findViewById<ImageButton>(R.id.imageButtonBack)
        imgBack.setOnClickListener {
            val intent : Intent = Intent(this, MenuDrawerActivity::class.java)
            startActivity(intent)
        }
    }
}