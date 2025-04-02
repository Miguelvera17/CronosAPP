package com.example.cronosapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.data.Alumno
import com.example.cronosapp.data.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModificarAlumnosActivity : AppCompatActivity() {

    private lateinit var retrofitService: RetrofitService
    private lateinit var etNombre: TextInputEditText
    private lateinit var etCorreo: TextInputEditText
    private lateinit var etContrasena: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_alumnos)

        retrofitService = RetrofitService.makeRetrofitService()

        etNombre = findViewById(R.id.editTextNombre)
        etCorreo = findViewById(R.id.editTextCorreo)
        etContrasena = findViewById(R.id.editTextContrasena)
        val btnGuardar = findViewById<Button>(R.id.buttonGuardar)
        val btnBack = findViewById<ImageButton>(R.id.imgButtonBack)

        val nombreActual = intent.getStringExtra("ALUMNO_ACTUAL") ?: ""

        cargarDatosActuales(nombreActual)

        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevoCorreo = etCorreo.text.toString()
            val nuevaContrasena = etContrasena.text.toString()

            if (validarCampos(nuevoNombre, nuevoCorreo, nuevaContrasena)) {
                mostrarDialogoConfirmacion(nombreActual, nuevoNombre, nuevoCorreo, nuevaContrasena)
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
    private fun cargarDatosActuales(nombreActual: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alumno = retrofitService.obtenerAlumno(nombreActual)
                withContext(Dispatchers.Main) {
                    etNombre.setText(alumno.nombre)
                    etCorreo.setText(alumno.correo)
                    etContrasena.setText("")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ModificarAlumnosActivity,
                        "Error al cargar datos: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun validarCampos(nombre: String, correo: String, contrasena: String): Boolean {
        if (nombre.isEmpty()) {
            etNombre.error = "Nombre requerido"
            return false
        }
        if (correo.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.error = "Correo inválido"
            return false
        }
        if (contrasena.isEmpty()) {
            etContrasena.error = "La contraseña no estar vacio";
            return false
        }
        return true
    }

    private fun modificarAlumno(nombreActual: String, nuevoNombre: String, nuevoCorreo: String, nuevaContrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val alumnoActualizado = Alumno(
                    nombre = nuevoNombre,
                    correo = nuevoCorreo,
                    contrasena = nuevaContrasena,
                    tipo = "alumno"
                )

                val alumnoModificado = retrofitService.modificarAlumno(nombreActual, alumnoActualizado)

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ModificarAlumnosActivity,
                        "Alumno modificado",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK)
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ModificarAlumnosActivity,
                        "Error al modificar: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun mostrarDialogoConfirmacion(nombreActual: String, nuevoNombre: String, nuevoCorreo: String, nuevaContrasena: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmar modificación")
        builder.setMessage("¿Estás seguro de que quieres modificar este alumno?")

        builder.setPositiveButton("Sí") { dialog, _ ->
            modificarAlumno(nombreActual, nuevoNombre, nuevoCorreo, nuevaContrasena)
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}