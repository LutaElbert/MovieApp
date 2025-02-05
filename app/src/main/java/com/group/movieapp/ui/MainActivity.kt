package com.group.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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



//fun getSampleMovies(): List<Movie> {
//    return listOf(
//        Movie(
//            id = 1,
//            title = "Inception",
//            releaseYear = 2010,
//            genre = "Sci-Fi, Action",
//            rating = 8.8,
//            imageUrl = "https://image.tmdb.org/t/p/w500/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg",
//            description = "A skilled thief, the best in the art of extraction, is given a chance to regain his old life if he can successfully perform an inception."
//        ),
//        Movie(
//            id = 2,
//            title = "Interstellar",
//            releaseYear = 2014,
//            genre = "Sci-Fi, Drama",
//            rating = 8.6,
//            imageUrl = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
//            description = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."
//        ),
//        Movie(
//            id = 3,
//            title = "The Dark Knight",
//            releaseYear = 2008,
//            genre = "Action, Crime, Drama",
//            rating = 9.0,
//            imageUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
//            description = "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on Gotham."
//        ),
//        Movie(
//            id = 4,
//            title = "Avatar",
//            releaseYear = 2009,
//            genre = "Action, Adventure, Fantasy",
//            rating = 7.8,
//            imageUrl = "https://image.tmdb.org/t/p/w500/kmcqlZGaSh20zpTbuoF0Cdn07dT.jpg",
//            description = "A paraplegic Marine dispatched to the moon Pandora becomes torn between following his orders and protecting the world he feels is his home."
//        ),
//        Movie(
//            id = 5,
//            title = "Titanic",
//            releaseYear = 1997,
//            genre = "Drama, Romance",
//            rating = 7.9,
//            imageUrl = "https://image.tmdb.org/t/p/w500/kHXEpyfl6zqn8a6YuozZUujufXf.jpg",
//            description = "A young couple from different social backgrounds fall in love aboard the ill-fated Titanic."
//        ),
//        Movie(
//            id = 6,
//            title = "The Matrix",
//            releaseYear = 1999,
//            genre = "Action, Sci-Fi",
//            rating = 8.7,
//            imageUrl = "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
//            description = "A hacker learns the shocking truth about reality and joins a rebellion against artificial intelligence."
//        ),
//        Movie(
//            id = 7,
//            title = "Joker",
//            releaseYear = 2019,
//            genre = "Crime, Drama, Thriller",
//            rating = 8.4,
//            imageUrl = "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
//            description = "A mentally troubled comedian embarks on a downward spiral of revolution and bloody crime in Gotham City."
//        ),
//        Movie(
//            id = 8,
//            title = "Avengers: Endgame",
//            releaseYear = 2019,
//            genre = "Action, Adventure, Sci-Fi",
//            rating = 8.4,
//            imageUrl = "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
//            description = "After the devastating events of Infinity War, the Avengers assemble once more to undo Thanos' actions and restore order."
//        ),
//        Movie(
//            id = 9,
//            title = "The Godfather",
//            releaseYear = 1972,
//            genre = "Crime, Drama",
//            rating = 9.2,
//            imageUrl = "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
//            description = "The aging patriarch of an organized crime dynasty transfers control of his empire to his reluctant son."
//        ),
//        Movie(
//            id = 10,
//            title = "Forrest Gump",
//            releaseYear = 1994,
//            genre = "Drama, Romance",
//            rating = 8.8,
//            imageUrl = "https://image.tmdb.org/t/p/w500/h5J4W4veyxMXDMjeNxZI46TsHOb.jpg",
//            description = "The life story of a slow-witted but kind-hearted man who unwittingly influences major historical events."
//        )
//    )
//}


//@Preview(widthDp = 500)
//@Preview(widthDp = 800)
//annotation class PreviewWidths

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainScreen()
    }
}