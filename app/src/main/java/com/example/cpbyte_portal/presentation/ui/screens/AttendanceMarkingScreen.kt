package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class AttendanceStatus { PRESENT, ABSENT, EXCUSED, NONE}

data class Member(
    val name: String,
    val libraryId: String,
    val attendancePercentage: Int,
    var status: AttendanceStatus = AttendanceStatus.NONE
)

@Preview(showBackground = true)
@Composable
fun AttendanceScreenPreview() {
    AttendanceScreen(title = "DSA", members = MembersDSA(), totalMembers = 30)
}

@Composable
fun AttendanceScreen(title: String, members: List<Member>, totalMembers: Int) {
    val verticalScroll = rememberScrollState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xff111111))
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(verticalScroll)
        ) {
            Text(
                text = "$title Attendance",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 25.dp, bottom = 20.dp)
            )

            Text(
                text = "Total members: $totalMembers",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 6.dp, bottom = 10.dp)
            )

            MembersTable(members)

        }
    }
}


//@Composable
//fun TabSelector(): String {
//    var selectedOption by remember { mutableStateOf("DSA") }
//    val options = listOf("DSA", "Development")
//
//    Row(
//        modifier = Modifier
//            .background(Color(0xff2e2e2e), RoundedCornerShape(8.dp))
//            .padding(4.dp),
//        horizontalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//        options.forEach { option ->
//            val isSelected = selectedOption == option
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(6.dp))
//                    .background(if (isSelected) Color(0xFF1e1e1e) else Color.Transparent)
//                    .clickable { selectedOption = option }
//                    .padding(horizontal = 12.dp, vertical = 8.dp)
//            ) {
//                Text(
//                    text = option,
//                    color = if (isSelected) Color.White else Color.Gray,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.W600
//                )
//            }
//        }
//    }
//    return selectedOption
//}



@Composable
fun MembersTable(members: List<Member>) {
    Row(modifier = Modifier.fillMaxSize()) {


        Column(modifier = Modifier.width(200.dp)) {
            TableHeader("Member")
            members.forEach { member ->
                MemberNamesCell(member.name)
            }
        }


        val scrollState = rememberScrollState()
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            Column(modifier = Modifier.width(150.dp)) {
                TableHeader("Library ID")
                members.forEach { member ->
                    TableCell(member.libraryId)
                }
            }

            Column(modifier = Modifier.width(140.dp)) {
                TableHeader("Attendance %")
                members.forEach { member ->
                    TableCell("${member.attendancePercentage}%")
                }
            }
            Column(modifier = Modifier.width(150.dp)) {
                TableHeader("Status")
                members.forEach { member ->
                    AttendanceStatusSelector()
                }
            }
        }
    }
}


@Composable
fun TableHeader(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(color = Color(0xFF115DE5)),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MemberNamesCell(value: String) {
    Text(
        text = value,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}


@Composable
fun TableCell(value: String) {
    Text(
        text = value,
        color = Color.White,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun AttendanceStatusSelector() {
    var status by remember { mutableStateOf(AttendanceStatus.NONE) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
        contentAlignment = Alignment.Center
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.dp)
        ) {
            StatusButton(
                icon = Icons.Filled.Check,
                color = Color(0xFF00C853),
                selected = status == AttendanceStatus.PRESENT
            ) { status = AttendanceStatus.PRESENT }

            StatusButton(
                icon = Icons.Filled.Close,
                color = Color.Red,
                selected = status == AttendanceStatus.ABSENT
            ) { status = AttendanceStatus.ABSENT }

            StatusButton(
                icon = Icons.Filled.AddCircle,
                color = Color.LightGray,
                selected = status == AttendanceStatus.EXCUSED
            ) { status = AttendanceStatus.EXCUSED }
        }
    }
}

@Composable
fun StatusButton(icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, selected: Boolean, Click: () -> Unit) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(if (selected) color else Color(0xFF1E1E1E))
            .border(1.dp, color, RoundedCornerShape(6.dp))
            .clickable { Click() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) Color.White else color,
            modifier = Modifier.size(18.dp)
        )
    }
}

fun MembersDSA(): List<Member> = listOf(

    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60)


)

fun MembersDEV(): List<Member> = listOf(
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60),
    Member("Manish Singh", "2428ME2551", 75),
    Member("Sanchit Gupta", "2428CSE2559", 85),
    Member("Pulkit Gupta", "2428CSE2551", 70),
    Member("Kartik Vishwakarma", "2428EC2559", 60)
)
//
//@Composable
//fun AttendanceScreen(group : String, ){
//
//    val title = "$group Attendance"
//
//    Scaffold { innerpadding ->
//        Column(modifier = Modifier
//            .background(color = Color(0xFF111111))
//            .padding(innerpadding)
//            .padding(10.dp)
//            .fillMaxSize()
//            ) {
//
//            ScreenTitle(title)
//
//            LazyColumn {
////                items(members){
////                    items -> {
////
////                    }
////                }
//            }
//
//
//        }
//    }
//
//}
//
//@Composable
//fun MembersRow(){
//
//}
//
//
//
//
//
//@Composable
//fun ScreenTitle(title : String){
//    Text(
//        text = title,
//        style = MaterialTheme.typography.titleLarge,
//        fontSize = 30.sp,
//        textAlign = TextAlign.Start,
//        fontWeight = FontWeight.W600,
//        color = Color.White,
//        modifier = Modifier
//            .padding(start = 25.dp, top = 40.dp, end = 30.dp)
//            .fillMaxWidth()
//            .width(50.dp)
//            .height(50.dp)
//    )
//}
//
//enum class AttendanceStatus {PRESENT, ABSENT, EXCUSED}
//
//data class Member(
//    val name: String,
//    val libraryId: String,
//    val attendancePercentage: Float,
//    var status: AttendanceStatus = AttendanceStatus.ABSENT
//)
//
//@Preview(showBackground = true)
//@Composable
//fun AttendanceScreenPreview(){
//    AttendanceScreen("Dsa")
//}
//
//
//
//data class DSA(
//    val name: String,
//    val libid: String,
//    val attended: Int,
//)
//
//fun DsaList(): MutableList<DSA> {
//    val list = mutableListOf<DSA>()
//    val members = remember {
//        mutableStateListOf(
//            Member("MraduI", "2428CSE2559", 43),
//            Member("Yavar", "2428CSE2555", 10),
//            Member("Pulkit", "2428EC2559", 11),
//            Member("Sanchit", "2428ME255", 21),
//        )
//    }
////    list.add(DSA("Sanchit", "2428CSE2559", 32))
////    list.add(DSA("Pulkit", "2428CSE255", 10))
////    list.add(DSA("Kartik", "2428EC2559", 11))
////    list.add(DSA("Manish", "2428ME255", 21))
////    list.add(DSA("Sanchit", "2428CSE2559", 43))
////    list.add(DSA("Pulkit", "2428CSE255", 10))
////    list.add(DSA("Kartik", "2428EC2559", 11))
////    list.add(DSA("Manish", "2428ME255", 21))
////    list.add(DSA("Sanchit", "2428CSE2559", 43))
////    list.add(DSA("Pulkit", "2428CSE255", 10))
////    list.add(DSA("Kartik", "2428EC2559", 11))
////    list.add(DSA("Manish", "2428ME255", 21))
////    return list;
//}
//
//data class DEV(
//    val name: String,
//    val libid: String,
//    val attended: Int,
//)
//
//fun DevList(): MutableList<DEV> {
//    val list = mutableListOf<DEV>()
//    list.add(DEV("Mradul", "2428CSE2559", 43))
//    list.add(DEV("Yavar", "2428CSE255", 10))
//    list.add(DEV("Pulkit", "2428EC2559", 11))
//    list.add(DEV("Sanchit", "2428ME255", 21))
//    list.add(DEV("Mradul", "2428CSE2559", 43))
//    list.add(DEV("Yavar", "2428CSE255", 10))
//    list.add(DEV("Pulkit", "2428EC2559", 11))
//    list.add(DEV("Sanchit", "2428ME255", 21))
//    list.add(DEV("Mradul", "2428CSE2559", 43))
//    list.add(DEV("Yavar", "2428CSE255", 10))
//    return list;
//}