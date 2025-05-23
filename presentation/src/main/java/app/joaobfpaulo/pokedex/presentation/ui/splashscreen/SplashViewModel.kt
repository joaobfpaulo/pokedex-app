package app.joaobfpaulo.pokedex.presentation.ui.splashscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.usecases.GetPokedexUseCase
import app.joaobfpaulo.pokedex.presentation.navigation.drawer.DrawerMenuItem
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route
import app.joaobfpaulo.pokedex.presentation.navigation.screens.Route.Companion.createRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: GetPokedexUseCase
) : ViewModel() {
    val isLoading = mutableStateOf(true)
    val hasError = mutableStateOf(false)
    val menus = mutableStateOf<List<DrawerMenuItem>?>(null)

    init {
        syncData()
    }

    private fun syncData() {
        viewModelScope.launch {
            useCase(cacheMode = CacheMode.CACHE)
                .catch {
                    Timber.e(it)
                    hasError.value = true
                    isLoading.value = false
                }
                .collect { pokedex ->
                    menus.value = pokedex.generations.map { entry ->
                        DrawerMenuItem(
                            title = entry.value,
                            route = Route.Generation.createRoute(entry.key.toString())
                        )
                    }
                    isLoading.value = false
                }
        }
    }
}