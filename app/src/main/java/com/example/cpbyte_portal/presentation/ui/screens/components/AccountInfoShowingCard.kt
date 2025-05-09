package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.fieldTextPrimaryColor
import com.example.cpbyte_portal.presentation.ui.theme.fieldTextSecondaryColor
import com.example.cpbyte_portal.presentation.ui.theme.imageTintColor
import com.example.cpbyte_portal.presentation.ui.theme.inputFieldBgColor


@Composable
fun AccountInfoShowingCard(
    title: String,
    textFieldValue: String,
    image: ImageVector
) {
    AccountScreenCard(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(70.dp)
        ,
        colors = CardDefaults.elevatedCardColors(containerColor = inputFieldBgColor),
        shape = RoundedCornerShape(7.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.width(15.dp))
                Icon(
                    imageVector = image,
                    contentDescription = title,
                    tint = imageTintColor,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(Modifier.width(15.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxSize(),
                ) {
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = title,
                        color = fieldTextPrimaryColor,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = textFieldValue,
                        color = fieldTextSecondaryColor,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Default ,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(Modifier.width(5.dp))

                }
            }
        }
    )
}