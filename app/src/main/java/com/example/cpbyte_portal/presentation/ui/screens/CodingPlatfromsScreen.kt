package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodingPlatformsScreen(
    initialUsernames: Map<String, String>,
    platformIcons: Map<String, Painter>
) {

    var usernames by remember { mutableStateOf(initialUsernames) }


    Scaffold { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .background(Color.Black)
                .padding(24.dp)
        ) {
            Text(
                text = "Coding Platforms",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Add your Coding Platforms. You'll need to verify them.",
                fontSize = 14.sp,
                color = Color(0xFF94A3B8),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    usernames.forEach { (platform, username) ->
                        Column() {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {

                                platformIcons[platform]?.let { icon ->
                                    Icon(
                                        painter = icon,
                                        contentDescription = "$platform Icon",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.White)
                                }

                                Spacer(modifier = Modifier.width(9.dp))

                                Text(
                                    text = platform,
                                    modifier = Modifier.width(110.dp).fillMaxWidth(),
                                    fontSize = 18.sp,
                                    color = Color.White,)
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                TextField(
                                    value = username,
                                    onValueChange = {
                                        usernames = usernames.toMutableMap().apply {
                                            this[platform] = it
                                        }
                                    },
                                    placeholder = {
                                        Text(
                                            "$platform Username", color = Color(0xFF64748B),
                                            fontSize = 14.sp,
                                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                        )
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xFF0F172A),
                                        cursorColor = Color.White,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    ),
                                    textStyle = TextStyle(color = Color.White),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(49.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Button(
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFF3B82F6
                                        )
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .height(44.dp)
                                        .width(90.dp)
                                ) {
                                    Text(
                                        "Add",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodingPlatformsScreenPreview() {
    val initialUsernames = mapOf(
        "Leetcode" to "",
//        "GFG" to "",
//        "Hackerrank" to "",
//        "Codechef" to "",
        "Github" to ""
    )
    val platformIcons = mapOf(
        "Leetcode" to painterResource(id = R.drawable.leetcode_logoo),
//        "GFG" to painterResource(id = R.drawable.geeksforgeeks_logoo),
//        "Hackerrank" to painterResource(id = R.drawable.hackerrank_logoo),
//        "Codechef" to painterResource(id = R.drawable.codechef_logoo),
        "Github" to painterResource(id = R.drawable.github))

    CodingPlatformsScreen(
        initialUsernames,platformIcons

    )
}