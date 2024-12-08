
package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate
import com.example.cronosapp.R

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferences)

        val buttonBack: ImageButton = findViewById(R.id.imageButtonBack)

        buttonBack.setOnClickListener {
            val intent = Intent(this, FichajeAlumnoActivity::class.java)
            startActivity(intent)
        }


        val spinner: Spinner = findViewById(R.id.spinner)


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.language_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }


        spinner.adapter = adapter


        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
