package com.group.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.group.movieapp.ui.screens.main.MainScreen
import com.group.movieapp.ui.screens.main.MainViewmodel
import com.group.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val viewModel: MainViewmodel by viewModels()
                MainScreen(viewModel)
            }
        }
    }
}

//@Preview(widthDp = 500)
//@Preview(widthDp = 800)
//annotation class PreviewWidths

@Preview(showBackground = true)
//@PreviewWidths
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainScreen()
    }
}