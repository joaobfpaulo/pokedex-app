package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokedexByGenerationUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(generation: Int, cacheMode: CacheMode) =
        repository.getPokedexByGeneration(generation = generation, cacheMode = cacheMode).flowOn(Dispatchers.IO)
}