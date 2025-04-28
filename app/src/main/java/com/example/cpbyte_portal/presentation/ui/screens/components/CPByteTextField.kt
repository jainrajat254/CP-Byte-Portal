package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.IconButton



@Composable
fun CPByteTextField(
    value: String,
    onValueChange: (String) -> Unit, // takes a String input and doesn't return anything
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    imeAction: ImeAction = ImeAction.Done,  // What happens when the user presses the action button

    ) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val isPassword = keyboardOptions.keyboardType == KeyboardType.Password
    val visualTransformation = if (isPassword && !passwordVisibility)
        PasswordVisualTransformation() //hides password
    else
        VisualTransformation.None //shows the text as it is
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        // label above the text field
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        // OutlineTextField forms a border around the selected field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange, //called when any change in the text field
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF262632),
                unfocusedContainerColor = Color(0xFF262632),
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisibility) "Hide password" else "Show password"
                    IconButton( onClick = {passwordVisibility = !passwordVisibility} ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = Color.LightGray
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF121212)),
            shape = RoundedCornerShape(15.dp)

        )

    }
}