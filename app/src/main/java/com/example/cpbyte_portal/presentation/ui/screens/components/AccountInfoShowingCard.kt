package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme

@Composable
fun AccountInfoShowingCard(
    title: String,
    textFieldValue: String,
    image: ImageVector
) {
    // Define a common shadowed card
    val borderColor = CPByteTheme.cardBorder

    AccountScreenCard(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(80.dp)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.elevatedCardColors(containerColor = CPByteTheme.inputFieldBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = image,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Column for title and value
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFamily
                )
                Text(
                    text = textFieldValue,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun AccountInfoShowingCardImage(
    title: String,
    textFieldValue: String,
    image: Int
) {
    // Define a common shadowed card
    val borderColor = Color(0xFF2C3E50)

    AccountScreenCard(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(80.dp)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.elevatedCardColors(containerColor = CPByteTheme.inputFieldBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Column for title and value
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFamily
                )
                Text(
                    text = textFieldValue,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily,
                    maxLines = 1
                )
            }
        }
    }
}
