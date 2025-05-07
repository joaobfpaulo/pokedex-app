package app.joaobfpaulo.pokedex.presentation.navigation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.usecases.SearchPokemonEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchPokemonEntriesUseCase
) : ViewModel() {
    private val _searchResultsStateFlow = MutableStateFlow<List<PokedexEntryModel>>(emptyList())
    val searchResultsStateFlow: StateFlow<List<PokedexEntryModel>> = _searchResultsStateFlow

    private fun updateState(state: List<PokedexEntryModel>) {
        _searchResultsStateFlow.value = state
    }

    fun search(query: String) {
        if (query.isEmpty()) {
            updateState(emptyList())
        } else {
            viewModelScope.launch {
                useCase(query).collect {
                    updateState(it)
                }
            }
        }
    }
}