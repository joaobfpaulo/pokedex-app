package app.joaobfpaulo.pokedex.data.remote.repository

import app.joaobfpaulo.pokedex.data.remote.model.response.mapper.GenerationMapper
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
    private val generationMapper: GenerationMapper,
    private val pokedexMapper: PokedexMapper,
    private val pokemonMapper: PokemonMapper,
) : IPokemonRepository {

    override fun getGenerationList(): Flow<Map<Int, String>> =
        provider.getGenerationList().map {
            generationMapper.mapGenerationListToGenerationListModel(it)
        }

    override fun getGenerationPokedex(generation: Int): Flow<PokedexModel> =
        provider.getGenerationInfo(generation).map {
            pokedexMapper.mapGenerationToPokedexModel(it.pokemonSpecies)
        }

    override fun getPokemonInfo(number: Int): Flow<PokemonModel> =
        provider.getPokemonInfo(number).map {
            pokemonMapper.mapPokemonInfoToPokemonModel(it)
        }
}