package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
fun AccountSetting(git stausUser_ID: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF070B0F)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .size(339.dp, 193.dp)
                .padding(42.dp, 14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            )

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .padding(21.dp, 15.dp, 0.dp, 0.dp)
                ) {
                    Image(
                        painter = painterResource(id = com.example.cpbyte_portal.R.drawable.profile_pic_logo),
                        contentDescription = "Profile Pic",
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                    Text(
                        text = "Profile Picture",
                        color = Color.White,
                        modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.padding((1.dp)))
                Row(
                    modifier = Modifier.padding(21.dp, 15.dp)
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(id = com.example.cpbyte_portal.R.drawable.cpbyte_logo),
                            contentDescription = "Profile Pic",
                            modifier = Modifier.size(68.dp, 68.dp)
                        )
                        Image(
                            painter = painterResource(id = com.example.cpbyte_portal.R.drawable.pfp_selector),
                            contentDescription = "Profile Pic",
                            modifier = Modifier
                                .size(23.38.dp, 23.38.dp)
                                .align(Alignment.BottomEnd)
                                .clickable { }
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        Text(
                            text = "Upload a new Profile Picture",
                            color = Color(0xffBABABA),
                        )
                        Text(
                            text = "Choose Image",
                            color = Color.White,
                        )
                        Text(
                            text = "Recommended: .jpeg, .png, .webp",
                            color = Color(0xff727272),
                        )
                    }
                }
            }
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