
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
import androidx.lifecycle.lifecycleScope
import com.example.cronosapp.R
import com.example.cronosapp.data.UsageDataStore
import kotlinx.coroutines.launch

class PreferencesActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferences)

        val buttonBack: ImageButton = findViewById(R.id.imageButtonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MenuDrawerActivity::class.java)
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

        usageDataStore = UsageDataStore(applicationContext)

        lifecycleScope.launch {
            usageDataStore.incrementUsageCount("Pref")
        }
    }
}
