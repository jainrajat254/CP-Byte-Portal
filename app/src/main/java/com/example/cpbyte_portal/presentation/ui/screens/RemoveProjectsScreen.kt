package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun RemoveProjectScreen() {

    var projectList by remember {
        mutableStateOf(
            listOf("Project Gamma", "Project Beta", "Project Alpha", "Project Gamma", "Project Beta", "Project Alpha")
        )
    }


    var showDialog by remember { mutableStateOf(false) }
    var projectToDelete by remember { mutableStateOf<String?>(null) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F172A))
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            Text(
                text = "Remove Project",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold, color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Manage your projects. Tap 'Remove' to delete a project.",
                fontSize = 14.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            projectList.forEach { project ->
                ProjectItem(
                    projectName = project,
                    onDelete = {
                        projectToDelete = project
                        showDialog = true
                    }
                )
            }


            if (showDialog && projectToDelete != null) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                projectList = projectList - projectToDelete!!
                                showDialog = false
                                projectToDelete = null },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF3B82F6)
                            )
                        ) {
                            Text("Yes", color = Color.White)
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = {
                                showDialog = false
                                projectToDelete = null
                            },
                            border = ButtonDefaults.outlinedButtonBorder,
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White
                            )
                        ) {
                            Text("No")
                        }
                    },
                    title = {
                        Text("Confirm Deletion", color = Color.White)
                    },
                    text = {
                        Text(
                            "Do you really want to delete \"$projectToDelete\"?",
                            color = Color.LightGray
                        )
                    },
                    containerColor = Color(0xFF1E293B),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}


@Composable
fun ProjectItem(projectName: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = projectName,
                color = Color.White, fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                shape = RoundedCornerShape(15), modifier = Modifier.size(height = 40.dp, width = 110.dp)
            ) {
                Text("Remove", color = Color.White)
            }
        }
    }
}


@Preview
@Composable
fun PreviewRemoveProjectScreen() {
    RemoveProjectScreen()
}

