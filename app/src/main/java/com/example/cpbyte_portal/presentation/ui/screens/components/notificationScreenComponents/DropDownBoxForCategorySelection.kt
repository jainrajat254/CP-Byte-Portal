package com.example.cpbyte_portal.presentation.ui.screens.components.notificationScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun DropDownBox(coordinatorRole: String, onclick: (String) -> Unit) {
    var options: List<String> = emptyList()
    if (coordinatorRole == "DSA") {
        options = listOf("General", "DSA")
    } else {
        options = listOf("General", "DEV")
    }

    var selectedOption by remember {
        mutableStateOf("Select")
    }
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black),
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF17191d),
                focusedContainerColor = Color(0xFF17191d),
                cursorColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            ),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(color = Color.Black)
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
//                    modifier = Modifier.background(color = Color.Black),
                    text = { Text(selectionOption, color = Color.White) },
                    onClick = {
                        selectedOption = selectionOption
                        expanded = false
                        onclick(selectedOption)
                    }
                )
            }
        }

    }
}

