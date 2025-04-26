package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AccountSetting(User_ID: String) {
        Card(
            modifier = Modifier
                .size(300.dp, 86.dp)
                .padding(42.dp, 109.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            )

        ) {
            Row {
                Image(
                    painter = painterResource(id = com.example.cpbyte_portal.R.drawable.profile_pic_logo),
                    contentDescription = "Profile Pic",
                    modifier = Modifier.padding()
                )
                Spacer(modifier = Modifier.padding((19.dp)))
                Text(
                    text = "Profile Picture",
//                    textAlign = Alignment.Center,
                    modifier = Modifier.padding(2.dp)
                )

            }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF070B0F)),
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Card(
            modifier = Modifier
                .size(300.dp, 86.dp)
//                .background(color = Color(0xFF0B1327))
        ) {
            Text(
                text = "Profile Picture"
            )
        }
    }
    textFields()
}

@Preview
@Composable
private fun Account_DetailPreview() {
    AccountSetting("2428CSIT1872")
}

@Composable
fun textFields() {

}