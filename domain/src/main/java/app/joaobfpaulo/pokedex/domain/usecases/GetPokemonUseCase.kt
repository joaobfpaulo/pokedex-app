package app.joaobfpaulo.pokedex.domain.usecases

import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    operator fun invoke(number: Int) =
        repository.getPokemonInfo(number = number)
}