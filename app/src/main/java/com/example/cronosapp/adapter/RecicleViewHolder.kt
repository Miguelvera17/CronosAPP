package com.example.cronosapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.R
import com.example.cronosapp.RecicleActivity
import com.example.cronosapp.Student

class RecicleViewHolder(view: View) :  RecyclerView.ViewHolder(view) {

    val studentName = view.findViewById<TextView>(R.id.textAlumno)

    fun render(listStudent : Student){
        studentName.text =listStudent.studentName
    }
}