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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.domain.model.DomainUser

@Composable
fun MemberDetail(
    index: Int,
    member: DomainUser,
    subject: String,
    onStatusChange: (String) -> Unit,
) {
    val percentage = if (subject == "DSA") {
        member.dsaAttendance
    } else {
        member.devAttendance
    }
    Column(
        modifier = Modifier
            .background(
                when (member.attendanceStatus) {
                    "PRESENT" -> Color(0xFF3C8A4E)
                    "ABSENT_WITHOUT_REASON" -> Color(0xFFAB1D36)
                    "ABSENT_WITH_REASON" -> Color(0xFF6081C2)
                    else -> Color(0xFF171F36)

                }
            )
            .fillMaxWidth()
            .padding(2.dp)
            .height(60.dp)
            .clip((RoundedCornerShape(5.dp))),
        verticalArrangement = Arrangement.Center
    ) {


        //row for arranging name,libId and attendance
        Row(verticalAlignment = Alignment.Bottom) {
            Spacer(Modifier.weight(0.2f))

            //index
            Text(
                text = "$index.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,

                modifier = Modifier
                    .size(20.dp)
                    .padding(top = 3.dp)
            )

            Spacer(Modifier.weight(0.2f))


            //member name
            Text(
                member.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0XFFE0F2FE),
                maxLines = 2,
                modifier = Modifier
                    .width(100.dp)
            )
            Spacer(Modifier.weight(0.8f))

            //member LibraryID
            Text(
                member.library_id, fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = Color(0XFFE0F2FE),
                modifier = Modifier.width(100.dp)
            )


            Spacer(Modifier.weight(1f))

            //member current attendance
            Text(
                "$percentage%",
                fontWeight = FontWeight.W600,
                color = Color(0XFFE0F2FE),
                fontSize = 12.sp,
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.weight(1.5f))
        }

        ReasonSelectionSwitch(member, onStatusChange)
    }
}
