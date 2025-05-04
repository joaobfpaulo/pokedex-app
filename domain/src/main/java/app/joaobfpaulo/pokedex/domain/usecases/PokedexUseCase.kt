package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokedexUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(limit: Int, offset: Int) =
        repository.getPokemonList(limit = limit, offset = offset)
}