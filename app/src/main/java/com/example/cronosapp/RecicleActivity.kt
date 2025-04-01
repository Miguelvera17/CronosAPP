package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.adapter.RecicleAdapter
import com.example.cronosapp.data.Alumno
import com.example.cronosapp.data.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecicleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecicleAdapter
    private lateinit var retrofitService: RetrofitService

    private val addAlumnoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            fetchAlumnos()
        }
    }

    private val modifyAlumnoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            fetchAlumnos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_vista)

        recyclerView = findViewById(R.id.rv_lista_edit)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecicleAdapter(emptyList()) { selectedStudent -> }
        recyclerView.adapter = adapter

        retrofitService = RetrofitService.makeRetrofitService()

        fetchAlumnos()

        findViewById<Button>(R.id.buttonAddAlumno).setOnClickListener {
            val intent = Intent(this, menu_anadir::class.java)
            addAlumnoLauncher.launch(intent)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            eliminarAlumnoSeleccionado()
        }

        findViewById<Button>(R.id.buttonModifyAlumno).setOnClickListener {
            modificarAlumnoSeleccionado()
        }

        findViewById<ImageButton>(R.id.imgButtonBack).setOnClickListener {
            finish()
        }
    }

    private fun fetchAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alumnos = retrofitService.listarAlumnos()
                withContext(Dispatchers.Main) {
                    adapter.updateData(alumnos)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al cargar alumnos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun eliminarAlumnoSeleccionado() {
        val alumnoSeleccionado = adapter.getSelectedStudent()
        if (alumnoSeleccionado == null) {
            Toast.makeText(this, "Selecciona un alumno primero", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                retrofitService.eliminarAlumno(alumnoSeleccionado.nombre)
                withContext(Dispatchers.Main) {
                    fetchAlumnos()
                    Toast.makeText(this@RecicleActivity, "Alumno eliminado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al eliminar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun modificarAlumnoSeleccionado() {
        val alumnoSeleccionado = adapter.getSelectedStudent()
        if (alumnoSeleccionado == null) {
            Toast.makeText(this, "Selecciona un alumno primero", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ModificarAlumnosActivity::class.java).apply {
            putExtra("ALUMNO_ACTUAL", alumnoSeleccionado.nombre)
        }
        modifyAlumnoLauncher.launch(intent)
    }
}