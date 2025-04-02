package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Stats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val btnBarChart = findViewById<Button>(R.id.btnBarChart)
        val btnPieChart = findViewById<Button>(R.id.btnPieChart)

        btnBarChart.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }

        btnPieChart.setOnClickListener {
            val intent = Intent(this, PieGraphActivity::class.java)
            startActivity(intent)
        }
    }
}
