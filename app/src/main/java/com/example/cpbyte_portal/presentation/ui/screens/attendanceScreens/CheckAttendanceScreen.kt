package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens.buildIsoDate
import com.example.cpbyte_portal.presentation.ui.screens.trackerScreens.textFieldColors
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.util.ResultState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CheckAttendanceScreen(
    coordinatorViewModel: CoordinatorViewModel,
    navController: NavHostController,
) {
    val todayDate = LocalDate.now()
    val scrollState = rememberScrollState()

    var expanded by remember { mutableStateOf(false) }
    var selectedSubject by remember { mutableStateOf("Select Subject") }
    val subjects = listOf("ANDROID", "ARVR", "CPP", "JAVA", "ML", "WEBDEV", "UIUX")
    val isoDate by remember {
        mutableStateOf(
            buildIsoDate(
                todayDate.year,
                todayDate.monthValue,
                todayDate.dayOfMonth
            )
        )
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }
    val checkStatusState by coordinatorViewModel.checkStatusState.collectAsState()

    // Track if check has been initiated
    var isCheckInitiated by remember { mutableStateOf(false) }

    LaunchedEffect(checkStatusState) {
        if (isCheckInitiated) {
            when (checkStatusState) {
                is ResultState.Success -> {
                    isDialog = false
                    val isMarked =
                        (checkStatusState as ResultState.Success<CheckStatusResponse>).data.marked
                    if (!isMarked) {
                        navController.navigate(
                            Routes.MarkAttendance.createRoute(subject = selectedSubject, isoDate)
                        )
                    } else {
                        Toast.makeText(
                            context, "Attendance already marked", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is ResultState.Failure -> {
                    isDialog = false
                    val error = (checkStatusState as ResultState.Failure).error.message.orEmpty()

                    Log.e("ATTENDANCE ERROR", error)

                    Toast.makeText(
                        context, "Some error occurred, please try again", Toast.LENGTH_SHORT
                    ).show()
                }

                ResultState.Idle -> {
                    isDialog = false
                }

                ResultState.Loading -> {
                    isDialog = true
                }
            }
        }
    }

    // Apply consistent colors
    Scaffold(
        bottomBar = {
            BottomBar(navController, currentRoute)
        },
        topBar = {
            CommonHeader(
                text = "Mark Attendance",
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
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

                    // 📅 Date Field
                    OutlinedTextField(
                        value = todayDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors()
                    )

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedSubject,
                            onValueChange = { selectedSubject = it },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            colors = textFieldColors()
                        )

                        ExposedDropdownMenu(expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            subjects.forEach { subject ->
                                DropdownMenuItem(text = { Text(subject) }, onClick = {
                                    selectedSubject = subject
                                    expanded = false
                                })
                            }
                        }
                    }

                    CPByteButton(
                        value = if (isDialog) "Checking..." else "Check Status",
                        onClick = {
                            val checkStatusRequest = CheckStatusRequest(
                                date = isoDate,
                                domain = selectedSubject
                            )
                            Log.d("ATTENDANCE", checkStatusRequest.toString())
                            coordinatorViewModel.checkStatus(checkStatusRequest = checkStatusRequest)
                            isCheckInitiated = true // Set flag to true when check is initiated
                        },
                        enabled = selectedSubject != "Select Subject" // Disable button if subject is not selected
                    )
                }
            }
        }
    }
}
