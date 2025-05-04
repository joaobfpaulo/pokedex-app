package app.joaobfpaulo.pokedex.data.remote.repository

import app.joaobfpaulo.pokedex.data.remote.model.response.mapper.PokedexMapper
import app.joaobfpaulo.pokedex.data.remote.model.response.mapper.PokemonMapper
import app.joaobfpaulo.pokedex.data.remote.provider.PokemonRemoteProvider
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val provider: PokemonRemoteProvider,
    private val pokedexMapper: PokedexMapper,
    private val pokemonMapper: PokemonMapper,
) : IPokemonRepository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<PokedexModel> =
        provider.getPokemonList(limit, offset).map {
            pokedexMapper.mapPokemonListToPokedexModel(it)
        }

    override fun getPokemonInfo(pokemonName: String): Flow<PokemonModel> =
        provider.getPokemonInfo(pokemonName).map {
            pokemonMapper.mapPokemonInfoToPokemonModel(it)
        }
}