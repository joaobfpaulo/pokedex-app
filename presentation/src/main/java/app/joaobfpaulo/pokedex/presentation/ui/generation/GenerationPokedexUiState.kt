package app.joaobfpaulo.pokedex.presentation.ui.generation

import app.joaobfpaulo.pokedex.domain.model.PokedexModel

sealed class GenerationPokedexUiState {
    data object Loading : GenerationPokedexUiState()
    data class Success(val pokedex: PokedexModel) : GenerationPokedexUiState()
    data class Error(val message: String): GenerationPokedexUiState()
}
