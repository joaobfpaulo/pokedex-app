package app.joaobfpaulo.pokedex.domain.repository

import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getGenerationList(): Flow<Map<Int, String>>

    fun getGenerationPokedex(generation: Int): Flow<PokedexModel>

    fun getPokemonInfo(number: Int): Flow<PokemonModel>
}