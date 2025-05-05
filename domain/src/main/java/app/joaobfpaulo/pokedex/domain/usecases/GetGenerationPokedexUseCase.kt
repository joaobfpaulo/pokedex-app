package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class GetGenerationPokedexUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(generation: Int) =
        repository.getGenerationPokedex(generation = generation)
}