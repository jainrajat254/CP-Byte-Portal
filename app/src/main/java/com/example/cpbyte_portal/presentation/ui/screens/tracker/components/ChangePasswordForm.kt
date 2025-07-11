package com.example.cpbyte_portal.presentation.ui.screens.tracker.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.ui.screens.components.CPBytePasswordField

@Composable
fun ChangePasswordForm(
    oldPassword: String,
    newPassword: String,
    confirmPassword: String,
    onOldPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // or surfaceColorAtElevation(6.dp)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            CPBytePasswordField(
                value = oldPassword,
                onValueChange = onOldPasswordChange,
                label = "Old Password"
            )

            CPBytePasswordField(
                value = newPassword,
                onValueChange = onNewPasswordChange,
                label = "New Password"
            )

            CPBytePasswordField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = "Confirm New Password"
            )
        }
    }
}