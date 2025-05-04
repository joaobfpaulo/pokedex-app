package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(pokemonName: String) =
        repository.getPokemonInfo(pokemonName = pokemonName)
}