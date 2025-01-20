package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ClasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clases)

        val bttonBack : ImageButton = findViewById(R.id.imgButtonBack)
        bttonBack.setOnClickListener {
            val intent = Intent(this, MenuDrawerActivity::class.java)
            startActivity(intent)
        }

        val butEdit1 : Button = findViewById(R.id.buttonEdit)
        butEdit1.setOnClickListener {
            val intent = Intent(this, RecicleActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        val butEdit2 : Button = findViewById(R.id.buttonEdit2)
        butEdit2.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        val butEdit3 : Button = findViewById(R.id.buttonEdit3)
        butEdit3.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        val butList : Button = findViewById(R.id.buttonList)
        butList.setOnClickListener {
            val intent = Intent(this, AsistenciaClaseActivity::class.java)
            startActivity(intent)
        }

        val butList2 : Button = findViewById(R.id.buttonList2)
        butList2.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }

        val butList3 : Button = findViewById(R.id.buttonList3)
        butList3.setOnClickListener {
            Toast.makeText(this, "En desarrollo", Toast.LENGTH_SHORT).show()
        }
    }
}