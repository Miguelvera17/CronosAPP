package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cronosapp.adapter.RecicleAdapter
import com.example.cronosapp.provider.RecicleProvider

class RecicleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicle_vista)

        val recyclerView: RecyclerView = findViewById(R.id.rv_lista_edit)
        val studentList = RecicleProvider.studentList
        val adapter = RecicleAdapter(studentList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val bttonBack: ImageButton = findViewById(R.id.imgButtonBack)
        bttonBack.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
        }

        val bttonSave: Button = findViewById(R.id.button)
        bttonSave.setOnClickListener {
            val intent = Intent(this, ClasesActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Alumnos eliminados", Toast.LENGTH_SHORT).show()
        }
    }

}
