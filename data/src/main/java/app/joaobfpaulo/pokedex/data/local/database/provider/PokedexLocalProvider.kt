package app.joaobfpaulo.pokedex.data.local.database.provider

import app.joaobfpaulo.pokedex.data.local.database.dao.PokedexDao
import app.joaobfpaulo.pokedex.data.local.database.dao.PokemonDao
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntryEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokedexLocalProvider @Inject constructor(
    private val pokedexDao: PokedexDao,
    private val pokemonDao: PokemonDao
) {
    fun insertPokedex(vararg generations: PokedexEntity) = flow {
        emit(pokedexDao.insertPokedex(*generations))
    }

    fun getPokedex() : Flow<List<PokedexEntity>> = pokedexDao.getPokedex()

    fun insertPokedexEntries(vararg generations: PokedexEntryEntity) = flow {
        emit(pokedexDao.insertEntry(*generations))
    }

    fun getPokedexByGeneration(generation: Int) = pokedexDao.getPokedexByGeneration(generation)

    fun searchPokedex(query: String) = pokedexDao.searchPokedex("%$query%")

    fun deletePokedex() = flow { emit(pokedexDao.deleteAll()) }

    fun insertPokemon(pokemon: PokemonDetailEntity) = flow {
        emit(pokemonDao.insert(pokemon))
    }

    fun deleteAllPokemon() = flow { emit(pokedexDao.deleteAll()) }

    fun getPokemonByNumber(number: Int): Flow<PokemonDetailEntity> =
        pokemonDao.getPokemonByNumber(number)
}