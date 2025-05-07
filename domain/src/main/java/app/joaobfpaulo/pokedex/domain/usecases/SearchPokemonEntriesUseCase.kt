package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchPokemonEntriesUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(query: String) = repository.searchPokemon(query).flowOn(Dispatchers.IO)
}