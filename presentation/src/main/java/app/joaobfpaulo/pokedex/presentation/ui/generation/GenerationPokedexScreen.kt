package app.joaobfpaulo.pokedex.presentation.ui.generation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route.Companion.createRoute
import app.joaobfpaulo.pokedex.presentation.ui.theme.RobotoCondensed
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.toBitmap

@Composable
fun GenerationPokedexScreen(
    generation: Int?,
    navController: NavController,
    viewModel: GenerationPokedexViewModel = hiltViewModel()
) {
    val state by viewModel.generationPokedexUiStateFlow.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        when(val immutableState = state) {
            is GenerationPokedexUiState.Success -> {
                Pokedex(
                    pokedex = immutableState.pokedex,
                    navController = navController
                )
            }
            is GenerationPokedexUiState.Error -> {
                //TODO error view
            }
            is GenerationPokedexUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier =  Modifier
                            .size(100.dp)
                            .align(Alignment.Center)
                    )
                }

                viewModel.loadGenerationPokedex(generation)
            }
        }
//            Spacer(modifier = Modifier.height(20.dp))
//            SearchBar(
//                hint = "Search...",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                viewModel.searchPokemonList(it)
//            }

    }
}

@Composable
fun Pokedex(
    pokedex: PokedexModel,
    navController: NavController
) {
    with(pokedex.entries) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            items(size) {
                PokedexEntry(
                    entry = this@with[it],
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun PokedexEntry(
    modifier: Modifier = Modifier,
    entry: PokedexEntryModel,
    navController: NavController,
    viewModel: GenerationPokedexViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate(
                    Route.Detail.createRoute(
                        dominantColor.toArgb().toString(),
                        entry.pokemonName,
                        entry.number.toString()
                    )
                )
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val painter = rememberAsyncImagePainter(
                entry.imageUrl
            )
            val state by painter.state.collectAsState()

            when (val immutable = state) {
                is AsyncImagePainter.State.Empty,
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator()
                }
                is AsyncImagePainter.State.Success -> {
                    viewModel.calcDominantColor(immutable.result.image.toBitmap()) { color ->
                        dominantColor = color
                    }
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painter,
                        contentDescription = entry.pokemonName
                    )
                }
                is AsyncImagePainter.State.Error -> {
                    Icon(
                        Icons.Outlined.Warning,
                        contentDescription = "Error", //TODO extract string
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Text(
                text = entry.pokemonName,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

//@Composable
//fun SearchBar(
//    modifier: Modifier = Modifier,
//    hint: String = "",
//    onSearch: (String) -> Unit = {}
//) {
//    var text by remember {
//        mutableStateOf("")
//    }
//    var isHintDisplayed by remember {
//        mutableStateOf(hint != "")
//    }
//
//    Box(modifier = modifier) {
//        BasicTextField(
//            value = text,
//            onValueChange = {
//                text = it
//                onSearch(it)
//            },
//            maxLines = 1,
//            singleLine = true,
//            textStyle = TextStyle(color = Color.Black),
//            modifier = Modifier
//                .fillMaxWidth()
//                .shadow(5.dp, CircleShape)
//                .background(Color.White, CircleShape)
//                .padding(horizontal = 20.dp, vertical = 12.dp)
//                .onFocusChanged {
//                    isHintDisplayed = it.isFocused && text.isEmpty()
//                }
//        )
//        if(isHintDisplayed) {
//            Text(
//                text = hint,
//                color = Color.LightGray,
//                modifier = Modifier
//                    .padding(horizontal = 20.dp, vertical = 12.dp)
//            )
//        }
//    }
//}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}