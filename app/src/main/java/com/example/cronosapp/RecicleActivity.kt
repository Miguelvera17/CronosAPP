package com.example.cronosapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.adapter.RecicleAdapter

class RecicleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.listAlumnos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecicleAdapter(RecicleProvider.studentList)
    }
}
