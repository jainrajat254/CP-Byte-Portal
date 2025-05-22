package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.domain.model.AttendanceStatus
import com.example.cpbyte_portal.domain.model.DomainUser
import com.example.cpbyte_portal.domain.model.MarkAttendance
import com.example.cpbyte_portal.domain.model.UpdateStatusRequest
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.util.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberAttendanceMarkingList(
    members: List<DomainUser>,
    date: String,
    subject: String,
    navController: NavHostController,
    onMemberUpdate: (Int, DomainUser) -> Unit,
    coordinatorViewModel: CoordinatorViewModel,
) {
    val context = LocalContext.current
    val markAttendanceState by coordinatorViewModel.markAttendanceState.collectAsState()
    var domain by remember {
        mutableStateOf(subject)
    }

    if (markAttendanceState is ResultState.Loading) {
        CustomLoader()
    }
    domain = if (subject == "JAVA" || subject == "CPP") {
        "DSA"
    } else {
        "DEV"
    }
    LaunchedEffect(markAttendanceState) {
        when (markAttendanceState) {
            is ResultState.Success -> {
                Toast.makeText(context, "Attendance marked successfully!", Toast.LENGTH_SHORT)
                    .show()
                val updateStatus = UpdateStatusRequest(
                    date = date,
                    domain = domain
                )
                coordinatorViewModel.updateStatus(updateStatus)
                navController.navigate(Routes.Home.route)
            }

            is ResultState.Failure -> {
                Toast.makeText(
                    context,
                    "Failed: ${(markAttendanceState as ResultState.Failure).error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "MARK_ATTENDANCE",
                    (markAttendanceState as ResultState.Failure).error.message.toString()
                )
            }

            else -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // List of members
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(5.dp))
        ) {
            itemsIndexed(members) { index, member ->
                val dismissState =
                    rememberSwipeToDismissBoxState(confirmValueChange = { dismissValue ->
                        val newStatus = when (dismissValue) {
                            SwipeToDismissBoxValue.StartToEnd -> "PRESENT"
                            SwipeToDismissBoxValue.EndToStart -> "ABSENT_WITHOUT_REASON"
                            else -> return@rememberSwipeToDismissBoxState false
                        }
                        onMemberUpdate(index, member.copy(attendanceStatus = newStatus))
                        false // prevent auto-dismiss
                    })

                SwipeToDismissBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    state = dismissState,
                    backgroundContent = {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    when (dismissState.dismissDirection) {
                                        SwipeToDismissBoxValue.StartToEnd -> Color(0xFF10B981)
                                        SwipeToDismissBoxValue.EndToStart -> Color(0xFFEF4444)
                                        else -> Color.Transparent
                                    }
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = when (dismissState.dismissDirection) {
                                SwipeToDismissBoxValue.StartToEnd -> Arrangement.Start
                                SwipeToDismissBoxValue.EndToStart -> Arrangement.End
                                else -> Arrangement.Start
                            }
                        ) {
                            Icon(
                                imageVector = when (dismissState.dismissDirection) {
                                    SwipeToDismissBoxValue.StartToEnd -> Icons.Default.CheckCircle
                                    SwipeToDismissBoxValue.EndToStart -> Icons.Default.Close
                                    else -> Icons.Default.CheckCircle
                                },
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                ) {
                    // Custom member item
                    MemberDetail(
                        index = index + 1,
                        member = member,
                        subject = domain,
                        onStatusChange = { newStatus ->
                            onMemberUpdate(index, member.copy(attendanceStatus = newStatus))
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val unmarkedCount = members.count { it.attendanceStatus == "NOT_MARKED" }

        CPByteButton(
            value = "Submit",
            onClick = {
                if (unmarkedCount > 0) {
                    Toast.makeText(
                        context,
                        "Mark all members attendance first",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    val markAttendance = MarkAttendance(
                        responses = members.map { member ->
                            AttendanceStatus(
                                library_id = member.library_id,
                                status = member.attendanceStatus
                            )
                        },
                        subject = domain
                    )
                    coordinatorViewModel.markAttendance(markAttendance = markAttendance)
                    Log.d("MARK_ATTENDANCE", markAttendance.toString())
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}
