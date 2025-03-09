package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_vista)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.rv_lista_edit)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el adaptador con una lista vacía
        adapter = RecicleAdapter(emptyList()) { selectedStudent ->
            // Manejar la selección de alumnos (puedes agregar lógica adicional si es necesario)
        }
        recyclerView.adapter = adapter

        // Inicializar RetrofitService
        retrofitService = RetrofitService.makeRetrofitService()

        // Obtener la lista de alumnos desde la API
        fetchAlumnos()

        // Configurar el botón "Eliminar alumnos"
        val buttonEliminar: Button = findViewById(R.id.button)
        buttonEliminar.setOnClickListener {
            eliminarAlumnoSeleccionado()
        }

        // Configurar el botón de volver
        val buttonVolver: ImageButton = findViewById(R.id.imgButtonBack)
        buttonVolver.setOnClickListener {
            // Navegar a la actividad anterior o cerrar esta actividad
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }

    private fun fetchAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Obtener la lista de alumnos desde la API
                val alumnos = retrofitService.listarAlumnos()

                // Actualizar el RecyclerView en el hilo principal
                withContext(Dispatchers.Main) {
                    adapter.updateData(alumnos)
                }
            } catch (e: Exception) {
                // Manejar errores (por ejemplo, problemas de red)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al obtener los alumnos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun eliminarAlumnoSeleccionado() {
        val alumnoSeleccionado = adapter.getSelectedStudent()

        if (alumnoSeleccionado == null) {
            Toast.makeText(this, "No hay ningún alumno seleccionado", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Eliminar el alumno seleccionado
                retrofitService.eliminarAlumno(alumnoSeleccionado.nombre)

                // Actualizar la lista de alumnos después de eliminar
                val alumnosActualizados = retrofitService.listarAlumnos()

                // Actualizar el RecyclerView en el hilo principal
                withContext(Dispatchers.Main) {
                    adapter.updateData(alumnosActualizados)
                    Toast.makeText(this@RecicleActivity, "Alumno eliminado correctamente", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Manejar errores (por ejemplo, problemas de red)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecicleActivity, "Error al eliminar el alumno: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun abrirMenuAnadir(view: View) {
        val intent = Intent(this, menu_anadir::class.java)
        startActivity(intent)
    }
}