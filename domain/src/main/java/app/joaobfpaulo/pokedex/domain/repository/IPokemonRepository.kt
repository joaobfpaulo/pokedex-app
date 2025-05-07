package app.joaobfpaulo.pokedex.domain.repository

import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getPokedex(cacheMode: CacheMode): Flow<PokedexModel>

    fun getPokemonByNumber(number: Int, cacheMode: CacheMode): Flow<PokemonModel>

    fun getPokedexByGeneration(generation: Int, cacheMode: CacheMode): Flow<List<PokedexEntryModel>>

    fun searchPokemon(query: String): Flow<List<PokedexEntryModel>>
}