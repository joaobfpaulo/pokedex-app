package app.joaobfpaulo.pokedex.presentation.ui.generation

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.usecases.GetPokedexByGenerationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenerationPokedexViewModel @Inject constructor(
    private val useCase: GetPokedexByGenerationUseCase
) : ViewModel() {
    private val _generationPokedexUiStateFlow =
        MutableStateFlow<GenerationPokedexUiState>(GenerationPokedexUiState.Loading)
    val generationPokedexUiStateFlow: StateFlow<GenerationPokedexUiState> = _generationPokedexUiStateFlow

    private fun updateState(state: GenerationPokedexUiState) {
        _generationPokedexUiStateFlow.value = state
    }

    fun loadGenerationPokedex(generation: Int?) {
        viewModelScope.launch {
            generation?.let {
                useCase(generation = generation, cacheMode = CacheMode.CACHE)
                    .catch {
                        Timber.e(it)
                        updateState(
                            GenerationPokedexUiState.Error(
                                it.message ?: it.localizedMessage.orEmpty()
                            )
                        )
                    }.collect { result ->
                        updateState(
                            GenerationPokedexUiState.Success(result)
                        )
                    }
            }
        }
    }

    fun calcDominantColor(bitmap: Bitmap, onFinish: (Color) -> Unit) {
        val bmp = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}