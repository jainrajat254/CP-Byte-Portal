import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.screens.components.MembersAttendanceBox
import java.time.LocalDate


@Preview
@Composable
fun AttendanceScreenPreview() {
    AttendanceScreen("DSA", LocalDate.now().toString())
}

@Composable
fun AttendanceScreen(selectedOption: String, date: String) {
    Scaffold() { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .background(Color(0xFF171F36))
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {


            Spacer(modifier = Modifier.padding(13.dp))

            //title of the Attendance Screen
            Text(
                text = "$selectedOption Attendance",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Spacer(Modifier.padding(25.dp))

            //box of members for marking attendance of particular domain and date
            MembersAttendanceBox(selectedOption = selectedOption, date = LocalDate.now().toString())
        }
    }
}


//data class for member
data class Member(
    val name: String,
    val libid: String,
    val attended: Int,
    var status: String = "NOT_MARKED"
)

fun DsaList(): MutableList<Member> {
    val list = mutableListOf<Member>()
    list.add(Member("Sanchit", "2428CSE2559", 32))
    list.add(Member("Pulkit", "2428CSE255", 10))
    list.add(Member("Kartik", "2428EC2559", 11))
    list.add(Member("Manish", "2428ME255", 21))
    list.add(Member("Sanchit", "2428CSE2559", 43))
    list.add(Member("Pulkit", "2428CSE255", 10))
    list.add(Member("Kartik", "2428EC2559", 11))
    list.add(Member("Manish", "2428ME255", 21))
    list.add(Member("Sanchit", "2428CSE2559", 43))
    list.add(Member("Pulkit", "2428CSE255", 10))
    list.add(Member("Kartik", "2428EC2559", 11))
    list.add(Member("Manish", "2428ME255", 21))
    return list;
}


fun DevList(): MutableList<Member> {
    val list = mutableListOf<Member>()
    list.add(Member("Pulkit", "2428CSE255", 10, "not marked"))
    list.add(Member("Kartik", "2428EC2559", 11, "not marked"))
    list.add(Member("Manish", "2428ME255", 21, "not marked"))
    list.add(Member("Sanchit", "2428CSE2559", 43, "not marked"))
    list.add(Member("Pulkit", "2428CSE255", 10, "not marked"))
    list.add(Member("Kartik", "2428EC2559", 11, "not marked"))
    list.add(Member("Manish", "2428ME255", 21, "not marked"))
    list.add(Member("Sanchit", "2428CSE2559", 43, "not marked"))
    list.add(Member("Pulkit", "2428CSE255", 10, "not marked"))
    list.add(Member("Kartik", "2428EC2559", 11, "not marked"))
    list.add(Member("Manish", "2428ME255", 21, "not marked"))
    return list;
}
