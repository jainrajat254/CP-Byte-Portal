package com.example.cpbyte_portal.presentation.ui.screens

import CPByteButton
import CPByteTextField
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cpbyte_portal.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame


//@Preview
@Composable
fun LoginScreen() {
    // val cannot be reassigned
    var libraryId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val image = painterResource(R.drawable.cpbyte_logo)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp), //Adds padding around the ecreen
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = image,
            contentDescription = "logo",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Start)

        )
        Spacer(
            modifier = Modifier
                .height(120.dp)
        )
        Text(
            text = stringResource(R.string.login_text),
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight(500),
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Text(
            text = stringResource(R.string.welcome_text),
            color = Color(0xFF0EC1E7),
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
        )
        Spacer(
            modifier = Modifier
                .height(80.dp)
        )

        // Input field for Library Id
        CPByteTextField(
            value = libraryId,
            label = stringResource(R.string.libraryId),
            onValueChange = { libraryId = it },
        )

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )

        // Input field for Password
        CPByteTextField(
            value = password,
            label = stringResource(R.string.password),
            onValueChange = { password = it },
        )

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )
        CPByteButton(
            value = stringResource(R.string.button_text),
            onClick = { }
        )
        Spacer(
            modifier = Modifier
                .height(150.dp)
        )

    }
}

