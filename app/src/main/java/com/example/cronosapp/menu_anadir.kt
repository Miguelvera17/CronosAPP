package com.example.cronosapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.data.Alumno
import com.example.cronosapp.data.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class menu_anadir : AppCompatActivity() {

    private lateinit var editTextNombre: TextInputEditText
    private lateinit var editTextCorreo: TextInputEditText
    private lateinit var editTextContrasena: TextInputEditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonVolver: ImageButton
    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_anadir)

        // Inicializar vistas
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextContrasena = findViewById(R.id.editTextContrasena)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonVolver = findViewById(R.id.imgButtonBack)

        // Inicializar RetrofitService
        retrofitService = RetrofitService.makeRetrofitService()

        // Configurar el botón de volver
        buttonVolver.setOnClickListener {
            // Cierra la actividad actual y vuelve a la anterior
            finish()
        }

        // Manejar clic en el botón "Guardar"
        buttonGuardar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()
            val contrasena = editTextContrasena.text.toString()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && contrasena.isNotEmpty()) {
                // Crear un objeto Alumno
                val alumno = Alumno(nombre, correo, contrasena, "alumno")

                // Llamar a la función para guardar el alumno
                guardarAlumno(alumno)
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarAlumno(alumno: Alumno) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Crear el alumno en la API usando el endpoint POST /alumnos
                retrofitService.crearAlumno(alumno)

                // Notificar éxito
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@menu_anadir, "Alumno guardado correctamente", Toast.LENGTH_SHORT).show()
                    finish() // Cerrar la actividad después de guardar
                }
            } catch (e: Exception) {
                // Manejar errores
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@menu_anadir, "Error al guardar el alumno: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}