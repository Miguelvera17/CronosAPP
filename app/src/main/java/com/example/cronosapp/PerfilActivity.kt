package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cronosapp.data.UsageDataStore
import kotlinx.coroutines.launch

class PerfilActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        val buttonModificar: Button = findViewById(R.id.buttonModify)
        val buttonSalir: Button = findViewById(R.id.buttonExit)
        val buttonVolver: ImageButton = findViewById(R.id.imgButtonBack)

        buttonSalir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonVolver.setOnClickListener {
            val intent = Intent(this, MenuDrawerActivity::class.java)
            startActivity(intent)
        }

        buttonModificar.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        usageDataStore = UsageDataStore(applicationContext)

        lifecycleScope.launch {
            usageDataStore.incrementUsageCount("Perfil")
        }
    }
}