package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.DomainUser
import com.example.cpbyte_portal.presentation.ui.screens.components.poppinsFamily
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Normal
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel

@Composable
fun MembersAttendanceBox(
    members: List<DomainUser>,
    subject: String,
    date: String,
    navController: NavHostController,
    coordinatorViewModel: CoordinatorViewModel,
    onMemberUpdate: (Int, DomainUser) -> Unit,
    onMarkAllPresent: (Boolean) -> Unit,
) {
    val totalMembers = members.size
    var allPresentChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),  // Matching color
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(Medium)
        ) {
            // Top Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Total Members = $totalMembers",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = poppinsFamily
                )

                Spacer(modifier = Modifier.weight(1f))

                Checkbox(
                    checked = allPresentChecked,
                    onCheckedChange = { checked ->
                        allPresentChecked = checked
                        onMarkAllPresent(checked)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.onSurface,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    stringResource(R.string.mark_all_present),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = ExtraSmall)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                    .padding(vertical = Normal, horizontal = Normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(0.2f))

                Text(
                    text = "Members",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Lib ID",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Attendance",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            MemberAttendanceMarkingList(
                subject = subject,
                coordinatorViewModel = coordinatorViewModel,
                members = members,
                date = date,
                navController = navController,
                onMemberUpdate = onMemberUpdate
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
