package app.joaobfpaulo.pokedex.data.remote.provider

import app.joaobfpaulo.pokedex.data.remote.api.PokeApiService
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteProvider @Inject constructor(
   private val api: PokeApiService
) {
    fun getPokemonList(limit: Int, offset: Int): Flow<PokemonList> = flow {
        emit(api.getPokemonList(limit, offset))
    }

    fun getPokemonInfo(pokemonName: String): Flow<PokemonInfo> = flow {
        emit(api.getPokemonInfo(pokemonName))
    }
}