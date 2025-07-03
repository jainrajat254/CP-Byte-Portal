package com.example.cpbyte_portal.presentation.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.domain.model.ProfileData

@Composable
fun DsaAndDevSection(
    mentorDsa: String?,
    mentorDev: String?,
    data: ProfileData,
) {
    if (mentorDsa != null || mentorDev != null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            mentorDsa?.let {
                Row {
                    Spacer(modifier = Modifier.width(2.dp))
                    SectionTitle("DSA")
                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MentorCard("Language", "${data.domain_dsa}")
                    MentorCard("Mentor", mentorDsa)
                }
            }
            mentorDev?.let {
                Row {
                    Spacer(modifier = Modifier.width(2.dp))
                    SectionTitle("DEV")
                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MentorCard("Language", "${data.domain_dev}")
                    MentorCard("Mentor", mentorDev)
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}