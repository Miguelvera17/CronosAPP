package com.example.cronosapp

import android.os.Bundle
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.example.cronosapp.data.UsageDataStore
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PieGraphActivity : AppCompatActivity() {

    private lateinit var usageDataStore: UsageDataStore
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_graph)

        usageDataStore = UsageDataStore(this)
        pieChart = findViewById(R.id.pieChart)

        loadUsageDataAndDisplayPieChart()
    }

    private fun loadUsageDataAndDisplayPieChart() {
        CoroutineScope(Dispatchers.IO).launch {
            val activities = listOf("Añadir", "Eliminar", "Editar")

            val activityData = mutableListOf<Pair<String, Int>>()

            activities.forEach { activity ->
                val count = usageDataStore.getUsageCount(activity)
                activityData.add(Pair(activity, count))
            }

            val sortedData = activityData.sortedBy { it.second }

            val pieEntries = sortedData.map { data ->
                PieEntry(data.second.toFloat(), data.first)
            }

            withContext(Dispatchers.Main) {
                val pieDataSet = PieDataSet(pieEntries, "Distribución de actividades")
                pieDataSet.colors = listOf(
                    Color.RED, Color.GREEN, Color.BLUE
                )
                val pieData = PieData(pieDataSet)
                pieChart.data = pieData
                pieDataSet.valueTextSize = 20f
                pieChart.animateY(1000, Easing.EaseInOutQuad)

                pieChart.invalidate()
            }
        }
    }
}
