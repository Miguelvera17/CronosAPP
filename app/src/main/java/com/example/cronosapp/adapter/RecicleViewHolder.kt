package com.example.cronosapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.Student
import com.example.cronosapp.databinding.ItemStudentBinding

class RecicleViewHolder(view: View) :  RecyclerView.ViewHolder(view) {

    val binding = ItemStudentBinding.bind(view)

    fun render(listStudent : Student){
        binding.textAlumno.text = listStudent.studentName
    }
}