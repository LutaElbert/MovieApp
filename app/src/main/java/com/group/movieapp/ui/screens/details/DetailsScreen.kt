package com.group.movieapp.ui.screens.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.group.movieapp.ui.components.ScaffoldComponent
import com.group.movieapp.ui.data.model.Movie
import com.group.movieapp.ui.screens.main.MainViewmodel
import com.group.movieapp.ui.screens.main.MovieUIState

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    mainViewmodel: MainViewmodel,
    detailsViewmodel: DetailsViewmodel
) {
    val state by mainViewmodel.networkState.collectAsState()
    val scope = rememberCoroutineScope()

    ScaffoldComponent {
        Box(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Bottom
                    )
                )
        ) {
            when (state) {
                MovieUIState.Loading -> {}
                is MovieUIState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Gray)
                    ) {
                        item {
                            val firstMovie =
                                (state as MovieUIState.Success).movies.firstOrNull() as Movie
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data("https://image.tmdb.org/t/p/w500/${firstMovie.imageUrl}")
                                    .crossfade(true)
                                    .build()
                            )

                            Image(
                                painter = painter,
                                contentDescription = "Movie Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(3 / 4f),
                                contentScale = ContentScale.Crop
                            )
                        }

                        item {

                        }
                    }
                }

                is MovieUIState.Error -> {
                    val errorMessage = (state as MovieUIState.Error).message
                    Log.d("DetailsScreen", "Error: $errorMessage")
                }
            }
        }
    }
}