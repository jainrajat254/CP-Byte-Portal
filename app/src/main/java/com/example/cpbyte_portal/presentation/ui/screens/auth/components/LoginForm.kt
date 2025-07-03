package com.example.cpbyte_portal.presentation.ui.screens.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.CPBytePasswordField
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField

@Composable
fun LoginForm(
    libraryId: String,
    onLibraryIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    CPByteTextField(
        value = libraryId.uppercase().trim(),
        label = stringResource(R.string.libraryId),
        hint = "2327CSE1241",
        onValueChange = {
            if (it.length <= 15) onLibraryIdChange(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Library ID Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    CPBytePasswordField(
        value = password,
        label = stringResource(R.string.password),
        hint = "Password",
        onValueChange = onPasswordChange,
    )
}
