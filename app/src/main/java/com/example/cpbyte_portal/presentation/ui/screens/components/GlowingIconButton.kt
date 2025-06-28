package com.example.cpbyte_portal.presentation.ui.screens.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.IconButton
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow

@Composable
fun GlowingIconButton(
    onClick: () -> Unit,  //Called when button is clicked
    icon: @Composable () -> Unit,  // Icon to display inside the button
    baseColor: Color,  // Button background color
    glowColor: Color,  // Color of the glow effect
    modifier: Modifier = Modifier,  //Allows customization from outside
) {
    // tracks user interactions
    val interactionSource =  remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    //animate shadow and border color when pressed
    val animatedElevation = animateDpAsState(if (isPressed.value) 16.dp else 0.dp)
    val animatedBorderColor = animateColorAsState(
        if (isPressed.value) glowColor else Color.Transparent
    )

    Box(
        modifier = modifier
            .size(36.dp)
            .shadow(
                elevation = animatedElevation.value,
                shape = RoundedCornerShape(8.dp),
                ambientColor = glowColor,
                spotColor = glowColor
            )
            .background(
                color = baseColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = animatedBorderColor.value, // Border glows when pressed
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null  //no ripple effect
            ),
        contentAlignment = Alignment.Center
    )
    {
        icon()  //Show whatever icon is passed in
    }
}
