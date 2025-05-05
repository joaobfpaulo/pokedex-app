package app.joaobfpaulo.pokedex.presentation.ui.pokemondetail

import app.joaobfpaulo.pokedex.domain.model.PokemonModel

sealed class PokemonUiState {
    data object Loading : PokemonUiState()
    data class Success(val pokemon: PokemonModel) : PokemonUiState()
    data class Error(val message: String): PokemonUiState()
}
