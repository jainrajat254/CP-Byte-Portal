package com.example.cpbyte_portal.presentation.ui.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectSettingsScreen() {
    // State variables for form fields and error handling
    var projectName by remember { mutableStateOf("") }
    var repoUrl by remember { mutableStateOf("") }
    var projectUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    // State to hold selected image URI
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    // Launcher for selecting image from gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    // Main UI scaffold for layout
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111111))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Heading
            Text(
                text = "Projects Settings",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))

            // Subheading
            Text(
                text = "Add your projects to showcase your work",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Box container for input card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF17191d))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Section Title
                    Text(
                        text = "Add Project",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    // Input for Project Name
                    RequiredLabel("Project Name:", modifier = Modifier.align(Alignment.Start))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            value = projectName,
                            onValueChange = { projectName = it },
                            placeholder = { Text("Enter your project name...") },
                            modifier = Modifier.fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp)),
                            colors = textFieldColors(),
                            shape = RoundedCornerShape(10.dp),
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Input for Respository URL
                        RequiredLabel("Repository URL:", modifier = Modifier.align(Alignment.Start))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            OutlinedTextField(
                                value = repoUrl,
                                onValueChange = { repoUrl = it },
                                placeholder = { Text("https://github.com/") },
                                modifier = Modifier.fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp)),
                                colors = textFieldColors(),
                                shape = RoundedCornerShape(10.dp)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Project URL
                            Text(
                                text = "Project URL:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(
                                    value = projectUrl,
                                    onValueChange = { projectUrl = it },
                                    placeholder = { Text("https://example.com") },
                                    modifier = Modifier.fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp)),
                                    colors = textFieldColors(),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                // Description input field
                                RequiredLabel(
                                    "Description:",
                                    modifier = Modifier.align(Alignment.Start)
                                )

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedTextField(
                                        value = description,
                                        onValueChange = { description = it },
                                        placeholder = { Text("Write a brief description of your project") },
                                        modifier = Modifier.fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp)),
                                        colors = textFieldColors(),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))

                                // Image picker for cover image
                                RequiredLabel("Cover Image:")
                                Text(
                                    text = "Only .jpg, .jpeg, .png files are allowed",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.align(Alignment.Start)
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                // Upload image button
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFF1F2937))
                                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
                                        .clickable { launcher.launch("image/*") },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (imageUri.value != null) {
                                        // Show selected image preview
                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                ImageRequest.Builder(LocalContext.current)
                                                    .data(imageUri.value)
                                                    .build()
                                            ),
                                            contentDescription = "Selected Image",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } else {
                                        // Show camera icon
                                        Icon(
                                            imageVector = Icons.Default.CameraAlt,
                                            contentDescription = "Upload",
                                            tint = Color.White
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))

                                // Submit Button
                                Button(
                                    onClick = {
                                        // Basic validation check
                                        if (projectName.isNotBlank() && repoUrl.isNotBlank() && description.isNotBlank() && imageUri.value != null) {
                                            showError = false
                                        } else {
                                            showError = true
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFF3B82F6
                                        )
                                    )
                                ) {
                                    Text("Add Project")
                                }

                                // Error message display
                                if (showError) {
                                    Text(
                                        text = "Please fill all required information",
                                        color = Color.Red,
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(top = 8.dp)
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
// Define colors for text fields to keep a consistent dark/light contrast style
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors(): TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
    containerColor = Color(0xFF1E293B),
    focusedTextColor = Color(0xFFF1F5F9),
    unfocusedTextColor = Color(0xFFE2E8F0),
    focusedPlaceholderColor = Color(0xFF94A3B8),
    unfocusedPlaceholderColor = Color(0xFF64748B),
    focusedBorderColor = Color(0xFF3B82F6),
    unfocusedBorderColor = Color(0xFF334155),
    focusedLabelColor = Color(0xFF3B82F6),
    unfocusedLabelColor = Color(0xFFCBD5E1),
    cursorColor = Color(0xFF3B82F6)
)


@Preview(showBackground = true)
@Composable
fun ProjectSettingsPreview(){
    MaterialTheme{
        ProjectSettingsScreen()
    }
}

// A reusable label component that appends a red * to required fields
@Composable
fun RequiredLabel(text: String, modifier: Modifier = Modifier){
    Text(
        text = buildAnnotatedString {
            append(text)
            withStyle(style = SpanStyle(color = Color.Red)){
                append(" *")
            }
        },
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        modifier = modifier
    )
}


