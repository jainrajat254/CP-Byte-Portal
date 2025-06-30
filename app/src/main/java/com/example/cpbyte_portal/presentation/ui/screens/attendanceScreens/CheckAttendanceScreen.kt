package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.domain.model.CheckStatusRequest
import com.example.cpbyte_portal.domain.model.CheckStatusResponse
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.components.CheckAttendanceCard
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.schedule.components.buildIsoDate
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Between
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.util.ResultState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CheckAttendanceScreen(
    coordinatorViewModel: CoordinatorViewModel,
    navController: NavHostController,
) {
    val todayDate = LocalDate.now()
    val scrollState = rememberScrollState()

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

    Scaffold(
        bottomBar = {
            BottomBar(navController, currentRoute)
        },
        topBar = {
            CommonHeader(
                text = "Mark Attendance",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = Between, vertical = Small),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CheckAttendanceCard(
                todayDate = todayDate,
                selectedSubject = selectedSubject,
                onSubjectSelected = { selectedSubject = it },
                onCheckStatusClick = {
                    val checkStatusRequest = CheckStatusRequest(
                        date = isoDate,
                        domain = selectedSubject
                    )
                    Log.d("ATTENDANCE", checkStatusRequest.toString())
                    coordinatorViewModel.checkStatus(checkStatusRequest = checkStatusRequest)
                    isCheckInitiated = true
                },
                isDialog = isDialog,
                subjects = subjects
            )
        }
    }
}
