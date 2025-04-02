package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.adapter.AsistenciaAdapter
import com.example.cronosapp.data.UsageDataStore
import com.example.cronosapp.provider.AsistenciaProvider
import kotlinx.coroutines.launch

class AsistenciaClaseActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia_clase)

        // Configurar botón de regresar
        val bttonBack: ImageButton = findViewById(R.id.imgButtonBack)
        bttonBack.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
        }

        // Configurar botón de guardar
        val bttonSave: Button = findViewById(R.id.buttonSave)
        bttonSave.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Asistencia guardada", Toast.LENGTH_SHORT).show()
        }

        // Configurar RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rv_lista_alumnnos_asistencia)
        val asistenciaList = AsistenciaProvider.asistenciaList
        val adapter = AsistenciaAdapter(asistenciaList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        usageDataStore = UsageDataStore(applicationContext)

        lifecycleScope.launch {
            usageDataStore.incrementUsageCount("Asistencia")
        }
    }
}
