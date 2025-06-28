package com.example.cpbyte_portal.presentation.ui.screens.tracker.components

import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import com.example.cpbyte_portal.presentation.ui.screens.components.convertUriToBase64
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding
import com.example.cpbyte_portal.presentation.ui.theme.Spacing
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed

@Composable
fun AddProjectForm(
    projectName: String,
    onProjectNameChange: (String) -> Unit,
    githubUrl: String,
    onGithubUrlChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    imageUri: Uri?,
    onImageUriChange: (Uri?) -> Unit,
    onBase64ImageUriChange: (String?) -> Unit,
) {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageUriChange(it)
            onBase64ImageUriChange(convertUriToBase64(context, it))
        } ?: Toast.makeText(context, "Image selection failed", Toast.LENGTH_SHORT).show()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppPadding.Medium)
        ) {

            CPByteTextField(
                value = projectName,
                onValueChange = onProjectNameChange,
                label = "Project Name",
                hint = "Enter your project name...",
            )

            Spacer(modifier = Modifier.height(Spacing.Small / 2))

            CPByteTextField(
                value = githubUrl,
                onValueChange = onGithubUrlChange,
                label = "Repository URL",
                hint = "https://github.com/username/repository"
            )


            Spacer(modifier = Modifier.height(Spacing.Small / 2))

            CPByteTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = "Description",
                hint = "Briefly describe what your project does..."
            )

            Spacer(modifier = Modifier.height(Spacing.Small))

            Text(
                buildAnnotatedString {
                    append("Cover Image")
                    withStyle(style = SpanStyle(color = WarningRed)) { append(" *") }
                    append("\n")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    ) {
                        append("(Only .jpg, .jpeg, .png files are allowed)")
                    }
                },
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(Spacing.Medium))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { launcher.launch("image/*") }
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(12.dp)
                    ),
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
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}