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

    // Registramos el lanzador para el resultado de añadir alumno
    private val addAlumnoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            fetchAlumnos() // Actualizamos la lista cuando volvemos con éxito
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_vista)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.rv_lista_edit)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el adaptador
        adapter = RecicleAdapter(emptyList()) { selectedStudent ->
            // Lógica de selección si es necesaria
        }
        recyclerView.adapter = adapter

        // Inicializar RetrofitService
        retrofitService = RetrofitService.makeRetrofitService()

        // Cargar datos iniciales
        fetchAlumnos()

        // Configurar botones
        findViewById<Button>(R.id.buttonAddAlumno).setOnClickListener {
            val intent = Intent(this, menu_anadir::class.java)
            addAlumnoLauncher.launch(intent)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            eliminarAlumnoSeleccionado()
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
                    val alumnoSeleccionado = adapter.getSelectedStudent()
                    adapter.updateData(alumnos)

                    // Volver a seleccionar el mismo alumno si aún existe
                    alumnoSeleccionado?.let { seleccionado ->
                        val nuevoSeleccionado = alumnos.find { it.nombre == seleccionado.nombre }
                        adapter.setSelectedStudent(nuevoSeleccionado)
                    }
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
                    fetchAlumnos() // Actualizamos la lista después de eliminar
                    Toast.makeText(this@RecicleActivity, "Alumno eliminado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al eliminar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun editarAlumnoSeleccionado() {
        val alumnoSeleccionado = adapter.getSelectedStudent()
        if (alumnoSeleccionado == null) {
            Toast.makeText(this, "Selecciona un alumno primero", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulación de cambios, podrías abrir otra actividad para obtener datos reales
        val cambios = mapOf(
            "correo" to "nuevoCorreo@example.com", // Aquí podrías poner datos del usuario
            "tipo" to "nuevoTipo"
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alumnoActualizado = retrofitService.editarAlumno(alumnoSeleccionado.nombre, cambios)
                withContext(Dispatchers.Main) {
                    fetchAlumnos() // Recargar la lista
                    Toast.makeText(this@RecicleActivity, "Alumno actualizado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al actualizar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}