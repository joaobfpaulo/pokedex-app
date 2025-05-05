package app.joaobfpaulo.pokedex.presentation.ui.generation

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import app.joaobfpaulo.pokedex.domain.usecases.GetGenerationPokedexUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GenerationPokedexViewModel @Inject constructor(
    private val useCase: GetGenerationPokedexUseCase
) : ViewModel() {
    private val _generationPokedexUiStateFlow =
        MutableStateFlow<GenerationPokedexUiState>(GenerationPokedexUiState.Loading)
    val generationPokedexUiStateFlow: StateFlow<GenerationPokedexUiState> = _generationPokedexUiStateFlow

    private fun updateState(state: GenerationPokedexUiState) {
        _generationPokedexUiStateFlow.value = state
    }

//    private var cachedPokemonList = listOf<PokedexEntryModel>()
//    private var isSearchStarting = true
//    var isSearching = mutableStateOf(false)

    //TODO implement cache and do search queries in cache
//    fun searchPokemonList(query: String) {
//        val listToSearch = if(isSearchStarting) {
//            pokemonList.value
//        } else {
//            cachedPokemonList
//        }
//        viewModelScope.launch(Dispatchers.Default) {
//            if(query.isEmpty()) {
//                pokemonList.value = cachedPokemonList
//                isSearching.value = false
//                isSearchStarting = true
//                return@launch
//            }
//            val results = listToSearch.filter {
//                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
//                        it.number.toString() == query.trim()
//            }
//            if(isSearchStarting) {
//                cachedPokemonList = pokemonList.value
//                isSearchStarting = false
//            }
//            pokemonList.value = results
//            isSearching.value = true
//        }
//    }

    fun loadGenerationPokedex(generation: Int?) {
        viewModelScope.launch {
            generation?.let {
                useCase(generation = generation)
                    .catch {
                        Timber.e(it)
                        updateState(
                            GenerationPokedexUiState.Error(
                                it.message ?: it.localizedMessage
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