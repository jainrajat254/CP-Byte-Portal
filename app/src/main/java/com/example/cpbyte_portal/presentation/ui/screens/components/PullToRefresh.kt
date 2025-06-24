package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnhancedPullToRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(isRefreshing) {
        if (isRefreshing && !pullToRefreshState.isRefreshing) {
            pullToRefreshState.startRefresh()
        } else if (!isRefreshing && pullToRefreshState.isRefreshing) {
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        content()

        // Only trigger refresh when user pulls down, not when isRefreshing changes
        if (pullToRefreshState.isRefreshing && !isRefreshing) {
            LaunchedEffect(pullToRefreshState.isRefreshing) {
                onRefresh()
            }
        }

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = CPByteTheme.brandCyan        )
    }
}
