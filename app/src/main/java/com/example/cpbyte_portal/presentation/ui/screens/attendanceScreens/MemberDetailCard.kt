package com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.DomainUser
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small


@Composable
fun MemberDetail(
    index: Int,
    member: DomainUser,
    subject: String,
    onStatusChange: (String) -> Unit,
) {
    val percentage = when (subject) {
        stringResource(R.string.dsa) -> member.dsaAttendance
        else -> member.devAttendance
    }

    Column(
        modifier = Modifier
            .background(
                when (member.attendanceStatus) {
                    stringResource(R.string.present) -> Color(0xFF3C8A4E)
                    stringResource(R.string.absent_without_reason) -> Color(0xFFAB1D36)
                    stringResource(R.string.absent_with_reason) -> Color(0xFF6081C2)
                    else -> Color(0xFF171F36)
                }
            )
            .fillMaxWidth()
            .padding(Small)
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$index.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(end = Small)
            )

            Text(
                member.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0XFFE0F2FE),
                maxLines = 1,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                member.library_id,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = Color(0XFFE0F2FE),
                modifier = Modifier.padding(end = Small)
            )

            Text(
                "$percentage%",
                fontWeight = FontWeight.W600,
                color = Color(0XFFE0F2FE),
                fontSize = 12.sp,
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        ReasonSelectionSwitch(member, onStatusChange)
    }
}
