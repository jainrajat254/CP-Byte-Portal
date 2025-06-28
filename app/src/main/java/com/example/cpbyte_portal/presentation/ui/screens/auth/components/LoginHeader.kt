package com.example.cpbyte_portal.presentation.ui.screens.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.theme.Spacing

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.welcome_text),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start,
            lineHeight = 36.sp,
            modifier = Modifier.weight(0.7f)
        )

        Spacer(modifier = Modifier.width(Spacing.Small))
        Image(
            painter = painterResource(R.drawable.cpbyte_logo),
            contentDescription = stringResource(R.string.logo_description),
            modifier = Modifier.weight(0.3f)
        )
    }
    Spacer(modifier = Modifier.height(Spacing.Small))

    Text(
        text = stringResource(R.string.login_text),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )
}