package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.cpbyte_portal.presentation.ui.screens.AttendanceDatagraph
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

//graphs
@Composable
fun AttendanceBarCard(attendanceList: List<AttendanceDatagraph>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF343B43),
                shape = RoundedCornerShape(12.dp)
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Shadow elevation
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)) // dark background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Attendance Trend",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Display the attendance graph
            AttendanceGraphCard(attendanceList)
        }
    }
}


@Composable
fun AttendanceGraphCard(attendanceList: List<AttendanceDatagraph>) {
    val context = LocalContext.current

    // bar entries for DSA attendance
    val dsaEntries = attendanceList.mapIndexed { index, data ->
        BarEntry(index.toFloat(), data.dsaPercentage)
    }

    //  bar entries for Development attendance
    val devEntries = attendanceList.mapIndexed { index, data ->
        BarEntry(index.toFloat(), data.devPercentage)
    }

    // Create BarDataSet for DSA and set the color
    val dsaDataSet = BarDataSet(dsaEntries, "DSA").apply {
        color = ContextCompat.getColor(context, android.R.color.holo_blue_light)
    }

    // Create BarDataSet for Development and set the color
    val devDataSet = BarDataSet(devEntries, "Development").apply {
        color = ContextCompat.getColor(context, android.R.color.holo_green_light)
    }

    // Combine both data sets into one BarData object
    val barData = BarData(dsaDataSet, devDataSet).apply {
        barWidth = 0.3f
    }

    // AndroidView is used to embed the native BarChart inside Compose
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { ctx ->
            BarChart(ctx).apply {
                this.data = barData
                description.isEnabled = false  // Hide the default description label

                // Configure X axis
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    valueFormatter = IndexAxisValueFormatter(attendanceList.map { it.week }) // Set week labels
                    granularity = 1f
                    setDrawGridLines(false) // Set week labels
                    textColor = android.graphics.Color.WHITE
                }

                // Configure Y axis
                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = 100f
                    granularity = 10f
                    textColor = android.graphics.Color.WHITE
                }

                axisRight.isEnabled = false // Hide right Y-axis
                legend.textColor = android.graphics.Color.WHITE // Legend text color


                // Group bars for side-by-side appearance
                groupBars(-0.4f, 0.2f, 0.05f)

                invalidate() // Refresh the chart
                //Redraw the chart immediately to reflect latest changes.
            }
        }
    )

}


