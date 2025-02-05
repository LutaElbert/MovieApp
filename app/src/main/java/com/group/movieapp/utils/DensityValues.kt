package com.group.movieapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Float.dpFromScreenHeight(): Dp {
    val screenHeightPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenHeightDp.dp.roundToPx()
    }
    val result = screenHeightPx * this
    return with(LocalDensity.current) { (result).toDp() }
}

@Composable
fun Float.dpFromScreenWidth(): Dp {
    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.roundToPx()
    }
    val result = screenWidthPx * this
    return with(LocalDensity.current) { (result).toDp() }
}