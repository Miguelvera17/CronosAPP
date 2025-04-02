package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cronosapp.data.UsageDataStore
import kotlinx.coroutines.launch

class FichajeAlumnoActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fichaje_alumno)

        val imgBack = findViewById<ImageButton>(R.id.imageButtonBack)
        imgBack.setOnClickListener {
            val intent : Intent = Intent(this, MenuDrawerActivity::class.java)
            startActivity(intent)
        }

        usageDataStore = UsageDataStore(applicationContext)

        lifecycleScope.launch {
            usageDataStore.incrementUsageCount("Gestion")
        }
    }
}