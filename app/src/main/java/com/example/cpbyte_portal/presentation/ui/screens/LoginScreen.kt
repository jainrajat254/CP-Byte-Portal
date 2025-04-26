package com.example.cpbyte_portal.presentation.ui.screens

import CPByteButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.cpbyte_portal.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import io.ktor.websocket.Frame


@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    // val cannot be reassigned
    var libraryId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val image = painterResource(R.drawable.cpbyte_logo)
//    val background = painterResource(R.drawable.login_bg)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_bg),
                contentScale = ContentScale.Crop
            )
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), //Adds padding around the screen
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {


                Spacer(
                    modifier = Modifier
                        .height((40.dp))
                )
                Row(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.welcome_text),
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .weight(0.7f)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                    Image(
                        painter = image,
                        contentDescription = "logo",
                        modifier = Modifier
                            .weight(0.3f),
                    )

                }

                Spacer(
                    modifier = Modifier
                        .height(14.dp)
                )
                Text(
                    text = stringResource(R.string.login_text),
                    color = Color(0xFF83888E),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontWeight = FontWeight(500),
                )
                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )

                // Input field for Library Id
                CPByteTextField(
                    value = libraryId,
                    label = stringResource(R.string.libraryId),
                    onValueChange = {
                        if (it.length <= 15) libraryId = it  // length of Library ID cannot be more than 15
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    imeAction = ImeAction.Next
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

                // Input field for Password
                CPByteTextField(
                    value = password,
                    label = stringResource(R.string.password),
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    imeAction = ImeAction.Done
                )

                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )
                CPByteButton(
                    value = stringResource(R.string.button_text),
                    onClick = { }
                )
            }
        }
    }
}

