package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.adapter.RecicleAdapter
import com.example.cronosapp.data.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecicleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_vista)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.rv_lista_edit)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el adaptador con una lista vacía
        adapter = RecicleAdapter(emptyList())
        recyclerView.adapter = adapter

        // Botón de volver
        val bttonBack: ImageButton = findViewById(R.id.imgButtonBack)
        bttonBack.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
        }

        // Botón de eliminar alumnos
        val bttonSave: Button = findViewById(R.id.button)
        bttonSave.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Alumnos eliminados", Toast.LENGTH_SHORT).show()
        }

        // Obtener la lista de alumnos desde la API
        fetchAlumnos()
    }

    private fun fetchAlumnos() {
        val retrofitService = RetrofitService.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Obtener la lista de alumnos desde la API
                val alumnos = retrofitService.listarAlumnos()

                // Extraer solo los nombres de los alumnos
                val nombresAlumnos = alumnos.map { it.nombre } // Esto convierte List<Alumno> a List<String>

                // Actualizar el RecyclerView en el hilo principal
                withContext(Dispatchers.Main) {
                    adapter.updateData(nombresAlumnos) // Pasar la lista de nombres al adaptador
                }
            } catch (e: Exception) {
                // Manejar errores (por ejemplo, problemas de red)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al obtener los alumnos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}