package com.group.movieapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.group.movieapp.ui.components.ScaffoldComponent
import com.group.movieapp.ui.data.model.Movie

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    movie: Movie? = null
) {
    ScaffoldComponent {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn {
                item {
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(movie?.imageUrl.toString())
                            .crossfade(true)
                            .build()
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Movie Image",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen()
}