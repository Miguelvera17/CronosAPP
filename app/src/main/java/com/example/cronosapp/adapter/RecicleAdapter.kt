package com.example.cronosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.R

class RecicleAdapter(private var studentList: List<String>) : RecyclerView.Adapter<RecicleAdapter.RecHolder>() {

    // ViewHolder para los elementos de la lista
    class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textAlumn) // Asegúrate de que este ID exista en tu layout
    }

    // Inflar el layout de cada ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_edit, parent, false)
        return RecHolder(view)
    }

    // Vincular los datos con las vistas
    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val studentName = studentList[position]
        holder.textView.text = studentName // Asigna el nombre del alumno
    }

    // Obtener el número de elementos en la lista
    override fun getItemCount(): Int {
        return studentList.size
    }

    // Método para actualizar los datos del RecyclerView
    fun updateData(newStudentList: List<String>) {
        studentList = newStudentList
        notifyDataSetChanged() // Notificar al RecyclerView que los datos han cambiado
    }
}