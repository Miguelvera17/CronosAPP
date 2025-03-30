package com.example.cronosapp.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cronosapp.data.Alumno
import com.example.cronosapp.data.RetrofitService
import kotlinx.coroutines.launch

class AlumnoViewModel(private val service: RetrofitService) : ViewModel() {

    var alumnos = mutableListOf<Alumno>()

    fun editarAlumno(nombre: String, nuevosDatos: Map<String, Any>, onSuccess: (List<Alumno>) -> Unit) {
        viewModelScope.launch {
            try {
                val alumnoActualizado = service.editarAlumno(nombre, nuevosDatos)
                alumnos.replaceAll { if (it.nombre == nombre) alumnoActualizado else it }
                onSuccess(alumnos)
            } catch (e: Exception) {
                println("Error editando alumno: ${e.message}")
            }
        }
    }
}