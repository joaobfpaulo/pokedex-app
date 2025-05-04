package app.joaobfpaulo.pokedex.domain.repository

import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<PokedexModel>

    fun getPokemonInfo(pokemonName: String): Flow<PokemonModel>
}