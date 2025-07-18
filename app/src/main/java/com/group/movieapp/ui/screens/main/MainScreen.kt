package com.group.movieapp.ui.screens.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.group.movieapp.ui.components.IconButtonComponent
import com.group.movieapp.ui.components.ScaffoldComponent
import com.group.movieapp.ui.data.model.Movie
import com.group.movieapp.utils.AnimatedScale
import com.group.movieapp.utils.GradientDirection
import com.group.movieapp.utils.dpFromScreenHeight
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewmodel: MainViewmodel = viewModel()) {
    ScaffoldComponent { paddingValues ->
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MovieListScreen(viewmodel)
            }
        }
    }
}

@Composable
fun MovieListScreen(viewmodel: MainViewmodel) {
    val uiState by viewmodel.networkState.collectAsState()
    val scope = rememberCoroutineScope()
    val clickedMovieCard = rememberSaveable { mutableStateOf<Movie?>(null) }

    val isExpanded: MutableState<Boolean> =
        rememberSaveable(clickedMovieCard) { mutableStateOf(true) }

    Box {
        when (uiState) {
            is MovieUIState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier.size(150.dp),
                            border = BorderStroke(
                                1.dp,
                                Brush.horizontalGradient(listOf(Color.Green, Color.Yellow))
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.Center),
                                    color = Color.Black,
                                    strokeCap = StrokeCap.Round
                                )

                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "Loading...",
                                    style = MaterialTheme.typography.bodyLarge
                                        .copy(color = Color.Black)
                                )
                            }
                        }
                    }
                }
            }

            is MovieUIState.Success -> {
                LaunchedEffect(true) {
                    scope.launch {
                        viewmodel.firstMovie.collect {
                            clickedMovieCard.value = it
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize()
                ) {
                    HeaderItem(clickedMovieCard.value, isExpanded)

                    BodyItem(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxHeight(0.5f)
                            .safeContentPadding(),
                        uiState = uiState) { movie ->
                        clickedMovieCard.value = movie
                        isExpanded.value = !isExpanded.value
                    }
                }
            }

            is MovieUIState.Error -> {
                Text(
                    "Error...${(uiState as MovieUIState.Error).message}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun BodyItem(
    modifier: Modifier = Modifier,
    uiState: MovieUIState,
    action: (Movie) -> Unit = {}
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
    ) {
        MovieActionButtons()
        MovieList(modifier = Modifier.weight(1f),uiState, action)
    }
}

@Composable
private fun MovieList(
    modifier: Modifier = Modifier,
    uiState: MovieUIState,
    action: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .wrapContentHeight()
    ) {
        items((uiState as MovieUIState.Success).movies.take(10)) { movie ->
            ContentItem(
                movie = movie,
                onClick = { action.invoke(movie) }
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun MovieActionButtons() {
    Box(
        modifier = Modifier
            .height(0.15f.dpFromScreenHeight())
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent, // Start transparent
                        Color.Black.copy(alpha = 0.8f) // Fade to semi-transparent black
                    ),
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AddToFavoritesAction(modifier = Modifier.weight(1f))
            PlayAction(modifier = Modifier.weight(1f))
            MoreInfoAction(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun AddToFavoritesAction(modifier: Modifier = Modifier) {
    IconButtonComponent(
        modifier = modifier, icon = {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = Color.White
            )
        },
        label = "Add to favorites"
    )
}

@Composable
private fun PlayAction(modifier: Modifier = Modifier) {
    IconButtonComponent(
        modifier = modifier, icon = {
            Surface (
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        "Play",
                        modifier = Modifier.wrapContentWidth(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
        label = ""
    )
}

@Composable
private fun MoreInfoAction(modifier: Modifier = Modifier) {
    IconButtonComponent(
        modifier = modifier, icon = {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = Color.White
            )
        },
        label = "More Info"
    )
}


@Composable
fun ContentItem(movie: Movie, onClick: (Movie) -> Unit = {}) {
    Card(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(0.8f),
                        Color.Black
                    )
                )
            )
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick.invoke(movie)
            }

    ) {
        MovieCardItem(movie)
    }
}

@Composable
fun HeaderItem(clickedMovieCard: Movie?, isExpanded: MutableState<Boolean>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/${clickedMovieCard?.imageUrl}")
                    .crossfade(true)
                    .build()
            )
            AnimatedScale(
                targetScale = 1.2f,
                durationMillis = 10000,
                animationTrigger = isExpanded.value,
                easing = FastOutSlowInEasing
            ) { scale ->
                Image(
                    painter = painter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale),
                    contentScale = ContentScale.Crop,
                    contentDescription = "null"
                )
            }
        }
    }
}

@Composable
fun MovieCardItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush =
                Brush.verticalGradient(
                    listOf(
                        Color.Gray,
                        Color.Black,
                        Color.Black
                    )
                )
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.imageUrl}",
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .aspectRatio(3 / 4f)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "Movie Image",
                contentScale = ContentScale.Crop
            )

            val rating = String.format("%.2f", movie.rating)

            Text(
                rating,
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = movie.title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 4.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.description.toString(),
                style = MaterialTheme.typography.bodySmall
                    .copy(color = Color.White)
            )
        }
    }
}

@Composable
fun FadingShadow(
    modifier: Modifier = Modifier,
    startColor: Color = Color.Black,
    endColor: Color = Color.Transparent,
    gradientDirection: GradientDirection = GradientDirection.BottomToTop
) {
    val brush = when (gradientDirection) {
        GradientDirection.BottomToTop -> Brush.verticalGradient(
            colors = listOf(startColor, endColor),
            startY = 0f,
            endY = Float.POSITIVE_INFINITY // Ensures smooth blending
        )

        GradientDirection.TopToBottom -> Brush.verticalGradient(
            colors = listOf(endColor, startColor),
            startY = 0f,
            endY = Float.POSITIVE_INFINITY
        )

        GradientDirection.LeftToRight -> Brush.horizontalGradient(
            colors = listOf(startColor, endColor),
            startX = 0f,
            endX = Float.POSITIVE_INFINITY
        )

        GradientDirection.RightToLeft -> Brush.horizontalGradient(
            colors = listOf(endColor, startColor),
            startX = 0f,
            endX = Float.POSITIVE_INFINITY
        )
    }

    Box(
        modifier = modifier.background(brush)
    )
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}