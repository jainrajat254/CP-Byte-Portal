package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.cpbyte_portal.domain.model.AddProjectRequest
import com.example.cpbyte_portal.domain.model.Project
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.convertUriToBase64
import com.example.cpbyte_portal.presentation.ui.theme.CPByteBlue

import com.example.cpbyte_portal.presentation.ui.theme.WarningRed
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddProjectScreen(
    trackerViewModel: TrackerViewModel,
    navController: NavController
) {
    var projectName by remember { mutableStateOf("") }
    var githubUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var base64ImageUri by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val addProjectState by trackerViewModel.addProjectState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            base64ImageUri = convertUriToBase64(context, it)
        } ?: Toast.makeText(context, "Image selection failed", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(addProjectState) {
        when (addProjectState) {
            is ResultState.Success -> {
                isDialog = false
                Toast.makeText(context, "Project added successfully!", Toast.LENGTH_SHORT).show()
                // Navigate or reset state if needed
            }

            is ResultState.Failure -> {
                isDialog = false
                Log.d(
                    "ADD PROJECT ERROR",
                    (addProjectState as ResultState.Failure).error.message.toString()
                )
                Toast.makeText(context, "Some error occurred, please try again", Toast.LENGTH_SHORT)
                    .show()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    if (isDialog) {
        CustomLoader(text = "Adding your Project...")
    } else {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                CommonHeader(text = "Add Project",
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                // --- Project Name Field ---
                RequiredLabel("Project Name")
                OutlinedTextField(
                    value = projectName,
                    onValueChange = { projectName = it },
                    placeholder = { Text("Enter your project name...", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // --- GitHub URL Field ---
                RequiredLabel("Repository URL")
                OutlinedTextField(
                    value = githubUrl,
                    onValueChange = { githubUrl = it },
                    placeholder = {
                        Text("https://github.com/username/repository", fontSize = 14.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // --- Description Field ---
                RequiredLabel("Description")
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text("Briefly describe what your project does...", fontSize = 14.sp)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // --- Image Picker ---
                Text(
                    buildAnnotatedString {
                        append("Cover Image")
                        withStyle(style = SpanStyle(color = WarningRed)) { append(" *") }
                        append("\n")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)) {
                            append("(Only .jpg, .jpeg, .png files are allowed)")
                        }
                    },
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    imageUri?.let { uri ->
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Selected Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } ?: Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Upload",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // --- Add Project Button ---
                val isFormValid = projectName.isNotBlank() && githubUrl.isNotBlank() &&
                        description.isNotBlank() && imageUri != null

                Button(
                    onClick = {
                        if (isFormValid) {
                            val request = AddProjectRequest(
                                project = Project(
                                    projectName = projectName.trim(),
                                    githubUrl = githubUrl.trim(),
                                    description = description.trim(),
                                    coverImage = base64ImageUri ?: ""
                                )
                            )
                            trackerViewModel.addProject(request)
                            showError = false
                        } else {
                            showError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFormValid) CPByteBlue else MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Add Project", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }

                // --- Error Message ---
                if (showError) {
                    Text(
                        text = "Please fill all required information",
                        color = WarningRed,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors(): TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
    containerColor = MaterialTheme.colorScheme.surface,
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedBorderColor = CPByteBlue,
    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
    focusedLabelColor = CPByteBlue,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
    cursorColor = CPByteBlue
)

@Composable
fun RequiredLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            append(text)
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("*")
            }
        },
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}
