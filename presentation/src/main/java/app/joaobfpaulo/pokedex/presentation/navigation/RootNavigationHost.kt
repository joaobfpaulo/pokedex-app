package app.joaobfpaulo.pokedex.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.joaobfpaulo.pokedex.presentation.navigation.screens.DOMINANT_COLOR_PATH
import app.joaobfpaulo.pokedex.presentation.navigation.screens.GENERATION_PATH
import app.joaobfpaulo.pokedex.presentation.navigation.screens.NAME_PATH
import app.joaobfpaulo.pokedex.presentation.navigation.screens.NUMBER_PATH
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route.Companion.getRoute
import app.joaobfpaulo.pokedex.presentation.ui.common.ErrorView
import app.joaobfpaulo.pokedex.presentation.ui.generation.GenerationPokedexScreen
import app.joaobfpaulo.pokedex.presentation.ui.pokemondetail.PokemonDetailScreen


@Composable
fun RootNavigationHost(
    topBarTitle: MutableState<String>,
    paddingValues: PaddingValues,
    selectedMenuRoute: String,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = selectedMenuRoute
    ) {
        composable(
            route = Route.Error.getRoute()
        ) {
            ErrorView()
        }
        composable(
            route = Route.Detail.getRoute(),
            arguments = listOf(
                navArgument(NAME_PATH) {
                    type = NavType.StringType
                },
                navArgument(NUMBER_PATH) {
                    type = NavType.IntType
                },
                navArgument(DOMINANT_COLOR_PATH) {
                    type = NavType.StringType
                    nullable = true
                },
            )
        ) {
            topBarTitle.value = it.arguments?.getString(NAME_PATH).orEmpty()

            val dominantColor = remember {
                it.arguments?.getString(DOMINANT_COLOR_PATH)?.let { color ->
                    Color(color.toInt())
                }
            }

            val number = remember {
                it.arguments?.getInt(NUMBER_PATH)
            }

            PokemonDetailScreen(
                dominantColor = dominantColor,
                number = number,
            )
        }
        composable(
            route = Route.Generation.getRoute(),
            arguments = listOf(
                navArgument(GENERATION_PATH) {
                    type = NavType.IntType
                }
            )
        ) {
            topBarTitle.value = String()

            val generation = remember {
                it.arguments?.getInt(GENERATION_PATH)
            }

            GenerationPokedexScreen(
                generation = generation,
                navController = navController
            )
        }
    }
}