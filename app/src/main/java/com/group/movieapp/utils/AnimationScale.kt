package com.group.movieapp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun AnimatedScale(
    targetScale: Float,
    durationMillis: Int = 300,
    easing: Easing = LinearEasing,
    animationTrigger: Boolean = true,
    content: @Composable (scale: Float) -> Unit // Content lambda
) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(animationTrigger) {
        scale.animateTo(
            targetValue = targetScale,
            animationSpec = tween(durationMillis = durationMillis, easing = easing)
        )
        scale.animateTo(
            targetValue = 1f, // Return to original scale
            animationSpec = tween(durationMillis = durationMillis, easing = easing)
        )
    }

    content(scale.value) // Call the content lambda with the current scale
}