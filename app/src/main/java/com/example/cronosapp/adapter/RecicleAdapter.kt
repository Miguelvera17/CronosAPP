package com.example.cronosapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.R.layout.item_student
import com.example.cronosapp.RecicleActivity
import com.example.cronosapp.Student

class RecicleAdapter(private val studentList: List<Student>) : RecyclerView.Adapter<RecicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecicleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecicleViewHolder(layoutInflater.inflate(item_student, parent, false))
    }

    override fun getItemCount(): Int  = studentList.size

    override fun onBindViewHolder(holder: RecicleViewHolder, position: Int) {
        val item = studentList[position]
        holder.render(item)
    }
}