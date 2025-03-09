package com.example.cronosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.R
import com.example.cronosapp.data.Alumno

class RecicleAdapter(
    private var studentList: List<Alumno>,
    private val onItemSelected: (Alumno?) -> Unit // Callback para manejar la selección
) : RecyclerView.Adapter<RecicleAdapter.RecHolder>() {

    private var selectedStudent: Alumno? = null // Almacena el alumno seleccionado

    // ViewHolder para los elementos de la lista
    class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textAlumn)
    }

    // Inflar el layout de cada ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_edit, parent, false)
        return RecHolder(view)
    }

    // Vincular los datos con las vistas
    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val student = studentList[position]

        // Mostrar el nombre del alumno
        holder.textView.text = student.nombre

        // Cambiar el fondo si el alumno está seleccionado
        holder.itemView.setBackgroundResource(
            if (student == selectedStudent) R.color.selected_item_color else android.R.color.transparent
        )

        // Manejar clics en el ítem
        holder.itemView.setOnClickListener {
            if (selectedStudent == student) {
                // Si el alumno ya está seleccionado, deseleccionarlo
                selectedStudent = null
            } else {
                // Seleccionar el nuevo alumno y deseleccionar el anterior
                selectedStudent = student
            }
            onItemSelected(selectedStudent) // Notificar la selección
            notifyDataSetChanged() // Actualizar la vista
        }
    }

    // Obtener el número de elementos en la lista
    override fun getItemCount(): Int {
        return studentList.size
    }

    // Método para actualizar los datos del RecyclerView
    fun updateData(newStudentList: List<Alumno>) {
        studentList = newStudentList
        selectedStudent = null // Limpiar la selección al actualizar los datos
        notifyDataSetChanged()
    }

    // Obtener el alumno seleccionado
    fun getSelectedStudent(): Alumno? {
        return selectedStudent
    }
}