package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.domain.model.DomainUser
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.ErrorMessage
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.util.ResultState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MarkAttendanceScreen(
    subject: String = "JAVA",
    date: String,
    navController: NavHostController,
    coordinatorViewModel: CoordinatorViewModel,
) {
    val membersOfDomainState by coordinatorViewModel.membersOfDomainState.collectAsState()
    val members = remember { mutableStateListOf<DomainUser>() }

    LaunchedEffect(Unit) {
        coordinatorViewModel.membersOfDomain(subject)
    }

    LaunchedEffect(membersOfDomainState) {
        if (membersOfDomainState is ResultState.Success) {
            val resultMembers = (membersOfDomainState as ResultState.Success).data.data
            resultMembers?.let {
                members.clear()
                members.addAll(it)
            }
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(text = "$subject Attendance",
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFF0F172A)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            when (membersOfDomainState) {
                is ResultState.Loading -> {
                    CustomLoader()
                }

                is ResultState.Success -> {
                    MembersAttendanceBox(
                        members = members,
                        subject = subject,
                        coordinatorViewModel = coordinatorViewModel,
                        date = date,
                        navController = navController,
                        onMemberUpdate = { index, updatedMember ->
                            members[index] = updatedMember
                        },
                        onMarkAllPresent = { isChecked ->
                            members.forEachIndexed { i, user ->
                                members[i] = user.copy(
                                    attendanceStatus = if (isChecked) "PRESENT" else "NOT_MARKED"
                                )
                            }
                        }
                    )
                }

                is ResultState.Failure -> {
                    val error = (membersOfDomainState as ResultState.Failure).error
                    ErrorMessage(
                        message = error.localizedMessage ?: "Unable to load data",
                        onRetry = { coordinatorViewModel.membersOfDomain(subject) }
                    )
                }

                ResultState.Idle -> {
                    Text(
                        "Initializing...",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}