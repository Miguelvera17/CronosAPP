package com.example.cronosapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.data.UsageDataStore
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import android.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GraphActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore
    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        usageDataStore = UsageDataStore(this)
        barChart = findViewById(R.id.barChart)

        loadUsageDataAndDisplayChart()
    }

    private fun loadUsageDataAndDisplayChart() {
        CoroutineScope(Dispatchers.IO).launch {
            val activities = listOf("Perfil", "Pref", "Gestion", "Resumen", "Clases", "Asistencia") // Aquí puedes definir tus actividades

            val barEntries = mutableListOf<BarEntry>()
            val labels = mutableListOf<String>()

            activities.forEachIndexed { index, activity ->
                val count = usageDataStore.getUsageCount(activity)
                barEntries.add(BarEntry(index.toFloat(), count.toFloat()))
                labels.add(activity)
            }

            withContext(Dispatchers.Main) {
                val barDataSet = BarDataSet(barEntries, "Uso de actividades")

                val colors = listOf(
                    Color.RED,
                    Color.GREEN,
                    Color.BLUE,
                    Color.YELLOW,
                    Color.CYAN,
                    Color.MAGENTA
                )
                barDataSet.setColors(colors)

                val barData = BarData(barDataSet)

                barChart.data = barData
                barChart.invalidate()
                barDataSet.valueTextSize = 16f
                barChart.animateY(1000)

                barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            }
        }
    }


}
