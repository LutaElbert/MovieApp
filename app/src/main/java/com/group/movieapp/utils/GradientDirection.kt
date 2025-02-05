package com.group.movieapp.utils

import androidx.compose.ui.geometry.Offset

enum class GradientDirection(val start: Offset, val end: Offset) {
    BottomToTop(Offset(0f, 1f), Offset(0f, 0f)),
    TopToBottom(Offset(0f, 0f), Offset(0f, 1f)),
    LeftToRight(Offset(0f, 0f), Offset(1f, 0f)),
    RightToLeft(Offset(1f, 0f), Offset(0f, 0f))
}