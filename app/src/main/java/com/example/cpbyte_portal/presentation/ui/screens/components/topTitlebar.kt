package com.example.cpbyte_portal.presentation.ui.screens.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R


@Composable
fun topTitlebar(user_image: Int, coord_name: String) {
    Row {

        //Side blue line
        Card(
            colors = CardDefaults.cardColors(
                Color(0xff0EC1E7),
                contentColor = Color(0xff0EC1E7)
            ),
            modifier = Modifier
                .padding(start = 5.dp)
                .size(width = 4.dp, height = 24.dp)
        ) {
            Text(text = "|")
        }


        //Title
        Text(
            text = "Mark Attendence",
            color = Color.White,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            modifier = Modifier.size(124.dp, 60.dp)
        )


        //user logo
        Image(
            painter = painterResource(id = user_image),
            contentDescription = "User Image",
            modifier = Modifier
                .padding(start = 80.dp)
                .size(40.dp)
                .clip(CircleShape)
        )


        //Column for arranging name and role
        var scroll = rememberScrollState()
        Column(modifier = Modifier.padding(top = 5.dp)) {
            Text(
                text = coord_name,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                color = Color.White,
                modifier = Modifier
                    .size(80.dp, 18.dp)
                    .horizontalScroll(scroll)
            )

            Text(
                text = "COORDINATOR",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xffBABABA)
            )
        }

    }
}


@Composable
@Preview
fun display(){
    topTitlebar(user_image = R.drawable.user, coord_name = "ansh")
}