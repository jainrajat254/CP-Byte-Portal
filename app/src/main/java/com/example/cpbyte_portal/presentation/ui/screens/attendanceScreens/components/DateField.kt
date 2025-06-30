package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateField(todayDate: LocalDate) {
    CPByteTextField(
        value = todayDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
        onValueChange = {},
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
    )
}
