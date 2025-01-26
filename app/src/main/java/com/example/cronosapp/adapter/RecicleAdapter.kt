package com.example.cronosapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.R
import com.example.cronosapp.R.layout.item_student_edit
import com.example.cronosapp.RecicleActivity
import com.example.cronosapp.Student

class RecicleAdapter(private val studentList: List<String>) : RecyclerView.Adapter<RecicleAdapter.RecHolder>() {

        class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.textAlumn)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_student_edit, parent, false)
            return RecHolder(view)
        }

        override fun onBindViewHolder(holder: RecHolder, position: Int) {
            val student = studentList[position]
            holder.textView.text = student
        }

        override fun getItemCount() = studentList.size
}