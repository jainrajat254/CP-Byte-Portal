package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CheckAttendanceCard(
    todayDate: LocalDate,
    selectedSubject: String,
    onSubjectSelected: (String) -> Unit,
    onCheckStatusClick: () -> Unit,
    isDialog: Boolean,
    subjects: List<String>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DateField(todayDate)

            SubjectDropdown(
                selectedSubject = selectedSubject,
                onSubjectSelected = onSubjectSelected,
                subjects = subjects
            )

            CPByteButton(
                value = if (isDialog) "Checking..." else "Check Status",
                onClick = onCheckStatusClick,
                enabled = selectedSubject != "Select Subject"
            )
        }
    }
}
