package app.joaobfpaulo.pokedex.presentation.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.usecases.GetPokemonByNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val useCase: GetPokemonByNumberUseCase
) : ViewModel() {
    private val _pokemonUiStateFlow = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val pokemonUiStateFlow: StateFlow<PokemonUiState> = _pokemonUiStateFlow

    private fun updateState(state: PokemonUiState) {
        _pokemonUiStateFlow.value = state
    }

    fun getPokemonInfo(number: Int?) {
        require(number != null) { "Pokemon number should not be null" }
        viewModelScope.launch {
            useCase(number = number, cacheMode = CacheMode.CACHE)
                .catch {
                    Timber.e(it)
                    updateState(PokemonUiState.Error(it.message ?: it.localizedMessage))
                }.collect {
                    updateState(PokemonUiState.Success(it))
                }
        }
    }
}