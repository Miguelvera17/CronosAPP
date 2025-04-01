package com.example.cronosapp

import android.os.Bundle
import android.widget.Button
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

    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_anadir)

        retrofitService = RetrofitService.makeRetrofitService()

        val etNombre = findViewById<TextInputEditText>(R.id.editTextNombre)
        val etCorreo = findViewById<TextInputEditText>(R.id.editTextCorreo)
        val etContrasena = findViewById<TextInputEditText>(R.id.editTextContrasena)

        findViewById<Button>(R.id.buttonGuardar).setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombre.isBlank() || correo.isBlank() || contrasena.isBlank()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoAlumno = Alumno(nombre, correo, contrasena, "alumno")
            mostrarDialogoConfirmacion(nuevoAlumno)
        }
    }

    private fun guardarAlumno(alumno: Alumno) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                retrofitService.crearAlumno(alumno)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@menu_anadir, "Alumno añadido", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK) // Indicamos éxito
                    finish() // Cerramos la actividad
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@menu_anadir, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun mostrarDialogoConfirmacion(alumno: Alumno) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Confirmar creación")
        builder.setMessage("¿Estás seguro de que quieres añadir este alumno?")

        builder.setPositiveButton("Sí") { dialog, _ ->
            guardarAlumno(alumno)
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss() // Cierra el diálogo sin hacer cambios
        }

        val dialog = builder.create()
        dialog.show()
    }
}