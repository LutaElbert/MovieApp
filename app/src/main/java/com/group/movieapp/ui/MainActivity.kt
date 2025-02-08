package com.group.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.group.movieapp.ui.data.model.Movie
import com.group.movieapp.ui.screens.details.DetailsScreen
import com.group.movieapp.ui.screens.details.DetailsViewmodel
import com.group.movieapp.ui.screens.main.MainViewmodel
import com.group.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val firstMovie = rememberSaveable() { mutableStateOf<Movie?>(null) }
                val viewModel: MainViewmodel by viewModels()
                val detailsViewModel: DetailsViewmodel by viewModels()
//                MainScreen(viewModel)

                LaunchedEffect(Unit) {
                    lifecycleScope.launch {
                        firstMovie.value = viewModel.getFirstMovie()
                    }
                }

                DetailsScreen(
                    mainViewmodel = viewModel,
                    detailsViewmodel = detailsViewModel
                )
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
//        DetailsScreen()
    }
}