package app.joaobfpaulo.pokedex.presentation.ui.generation

import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel

sealed class GenerationPokedexUiState {
    data object Loading : GenerationPokedexUiState()
    data class Success(val pokedex: List<PokedexEntryModel>) : GenerationPokedexUiState()
    data class Error(val message: String): GenerationPokedexUiState()
}
