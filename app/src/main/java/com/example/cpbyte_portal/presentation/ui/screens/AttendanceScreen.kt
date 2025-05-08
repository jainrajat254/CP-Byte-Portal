package com.example.cpbyte_portal.presentation.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Preview
@Composable
fun AttendanceScreen(date: String = "04/05/2024") {
    val horizontalScroll = rememberScrollState()
    val verticalScroll = rememberScrollState()
    Scaffold() { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .background(Color(0xff17191C))
                .fillMaxSize()
                .padding(start = 5.dp)
                .horizontalScroll(horizontalScroll)
                .verticalScroll(verticalScroll)
        ) {


            Spacer(modifier = Modifier.padding(10.dp))


            ScreenTitle("Attendance")


            Spacer(Modifier.padding(12.dp))


            var selectedOption by remember { mutableStateOf("DSA") }
            val options = listOf("DSA", "Development")

            selectedOption = TabSelector()


            Spacer(Modifier.padding(5.dp))

            MembersList(selectedOption)
        }

    }
}


@Composable
fun TabSelector(): String {
    var selectedOption by remember { mutableStateOf("DSA") }
    val options = listOf("DSA", "Development")

    Row(
        modifier = Modifier
            .background(Color(0xff2e2e2e), RoundedCornerShape(8.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        options.forEach { option ->
            val isSelected = selectedOption == option
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isSelected) Color(0xFF1e1e1e) else Color.Transparent)
                    .clickable { selectedOption = option }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = option,
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
    return selectedOption
}


@Composable
fun ScreenTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}


@Composable
fun MembersList(selectedOption: String) {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent, Color.Gray),
        modifier = Modifier
            .width(360.dp),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column() {
            Row(Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)) {

                Text1("Member", Modifier.width(100.dp))
                Spacer(Modifier.weight(1f))


                Text1("Library ID", Modifier.width(95.dp))
                Spacer(Modifier.weight(1.5f))


//                Text1("Attended", Modifier.width(70.dp))
//                Spacer(Modifier.weight(1f))


                Text1("Status", Modifier.width(90.dp))
                Spacer(modifier = Modifier.weight(0.5f))
            }
            if (selectedOption == "DSA")
                Column {
                    DsaList().map { user ->
                        MemberDetail(user.name, user.libid, user.attended)
                    }
                }
            else
                Column {
                    DevList().map { user ->
                        MemberDetail(user.name, user.libid, user.attended)
                    }
                }
        }
    }
}


@Composable
private fun Text1(text: String, modifier: Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}

@Composable
fun MemberDetail(name: String, libId: String, attended: Int) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(10.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600,
                color = Color.White,
                modifier = Modifier
                    .width(100.dp)
            )

            Spacer(Modifier.padding(1.dp))

            Text(
                libId, fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = Color(0xB2F5F0F0),
                modifier = Modifier.width(100.dp)
            )
        }
//        Spacer(Modifier.weight(1f))
//
//        Text(
//            "$attended", fontWeight = FontWeight.W600,
//            color = Color.White,
//            modifier = Modifier.width(70.dp),
//            textAlign = TextAlign.Center
//        )
        Spacer(Modifier.weight(1.5f))

        var status by remember { mutableStateOf("not marked") }
        Box(modifier = Modifier.width(100.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                //Green Present Button
                val isPresent = status == "present"
                Box(
                    modifier = Modifier
                        .clickable { status = "present" }
                        .size(28.dp)
                        .background(
                            if (!isPresent) Color(0xFF1E1E1E) else Color(0xFF00A813),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(1.dp, Color(0xFF00C853), shape = RoundedCornerShape(6.dp))

                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = "Check",
                        tint = if (!isPresent) Color(0xFF00C853) else Color.White,// Green
                    )
                }

                Spacer(Modifier.weight(1f))
                // Red Absent Button
                val isAbsent = status == "absent"

                Box(
                    modifier = Modifier
                        .clickable { status = "absent" }
                        .size(28.dp)
                        .background(
                            if (!isAbsent) Color(0xFF1E1E1E) else Color.Red,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(1.dp, Color.Red, shape = RoundedCornerShape(6.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier.align(Alignment.Center),
                        tint = if (!isAbsent) Color.Red else Color.White // Red
                    )
                }

                Spacer(Modifier.weight(1f))
                // Blue excused button
                val isExcused = status == "excused"
                Box(
                    modifier = Modifier
                        .clickable { status = "excused" }
                        .size(28.dp)
                        .background(
                            if (!isExcused) Color(0xFF1E1E1E) else Color.Blue,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .border(1.dp, Color.Blue, shape = RoundedCornerShape(6.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.RadioButtonUnchecked,
                        contentDescription = "Close",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp),
                        tint = if (!isExcused) Color.Blue else Color.White
                    )
                }
            }
        }
    }
}

data class DSA(
    val name: String,
    val libid: String,
    val attended: Int,
)

fun DsaList(): MutableList<DSA> {
    val list = mutableListOf<DSA>()
    list.add(DSA("Sanchit", "2428CSE2559", 32))
    list.add(DSA("Pulkit", "2428CSE255", 10))
    list.add(DSA("Kartik", "2428EC2559", 11))
    list.add(DSA("Manish", "2428ME255", 21))
    list.add(DSA("Sanchit", "2428CSE2559", 43))
    list.add(DSA("Pulkit", "2428CSE255", 10))
    list.add(DSA("Kartik", "2428EC2559", 11))
    list.add(DSA("Manish", "2428ME255", 21))
    list.add(DSA("Sanchit", "2428CSE2559", 43))
    list.add(DSA("Pulkit", "2428CSE255", 10))
    list.add(DSA("Kartik", "2428EC2559", 11))
    list.add(DSA("Manish", "2428ME255", 21))
    return list;
}

data class DEV(
    val name: String,
    val libid: String,
    val attended: Int,
)

fun DevList(): MutableList<DEV> {
    val list = mutableListOf<DEV>()
    list.add(DEV("Mradul", "2428CSE2559", 43))
    list.add(DEV("Yavar", "2428CSE255", 10))
    list.add(DEV("Pulkit", "2428EC2559", 11))
    list.add(DEV("Sanchit", "2428ME255", 21))
    list.add(DEV("Mradul", "2428CSE2559", 43))
    list.add(DEV("Yavar", "2428CSE255", 10))
    list.add(DEV("Pulkit", "2428EC2559", 11))
    list.add(DEV("Sanchit", "2428ME255", 21))
    list.add(DEV("Mradul", "2428CSE2559", 43))
    list.add(DEV("Yavar", "2428CSE255", 10))
    return list;
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun date() {
//    val datePickerState = rememberDatePickerState()
//    DatePicker(
//        state = datePickerState,
//        colors = DatePickerDefaults.colors(titleContentColor = Color.Blue, containerColor = Color.Red)
//    )
//}
