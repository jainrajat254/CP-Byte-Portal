package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel

@Composable
fun LeaderboardContent(
    vm: TrackerViewModel,
    navController: NavController,
    showFilterDialog: Boolean,
    setShowFilterDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        ClubLeaderboard(
            modifier = modifier,
            vm = vm,
            navController = navController,
            showFilterDialog = showFilterDialog,
            setShowFilterDialog = setShowFilterDialog
        )
    }
}
