package app.joaobfpaulo.pokedex.data.remote.repository

import app.joaobfpaulo.pokedex.data.local.database.provider.PokedexLocalProvider
import app.joaobfpaulo.pokedex.data.remote.model.response.mapper.PokedexMapper
import app.joaobfpaulo.pokedex.data.remote.model.response.mapper.PokemonMapper
import app.joaobfpaulo.pokedex.data.remote.provider.PokedexRemoteProvider
import app.joaobfpaulo.pokedex.domain.cache.CacheMode
import app.joaobfpaulo.pokedex.domain.cache.cachedDataFlow
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val remoteProvider: PokedexRemoteProvider,
    private val localProvider: PokedexLocalProvider,
    private val pokedexMapper: PokedexMapper,
    private val pokemonMapper: PokemonMapper,
) : IPokemonRepository {

    override fun getPokedex(cacheMode: CacheMode): Flow<PokedexModel> = cachedDataFlow(
        cacheMode = cacheMode,
        isCached = {
            localPokedexDataFlow().firstOrNull()?.generations.orEmpty().isNotEmpty()
        },
        getAllCached = {
            localPokedexDataFlow().distinctUntilChanged().collect { emit(it) }
        },
        serverSync = {
            remoteProvider.getGenerationList().map {
                pokedexMapper.mapGenerationListToPokedexEntityArray(it)
            }.onEach { pokedexEntities ->
                localProvider.insertPokedex(*pokedexEntities).first()
                merge(
                    *pokedexEntities.map { pokedex ->
                        getPokedexByGeneration(pokedex.generation, CacheMode.SERVER_SYNC)
                    }.toTypedArray()
                ).first()
            }.collect()
        }
    )

    private fun localPokedexDataFlow() = localProvider.getPokedex().map {
        pokedexMapper.mapPokemonEntityListToPokedexModel(it)
    }

    override fun getPokedexByGeneration(
        generation: Int,
        cacheMode: CacheMode
    ): Flow<List<PokedexEntryModel>> = cachedDataFlow(
        cacheMode = cacheMode,
        isCached = {
            localPokedexEntryDataFlow(generation).firstOrNull().orEmpty().isNotEmpty()
        },
        getAllCached = {
            localPokedexEntryDataFlow(generation).distinctUntilChanged().collect { emit(it) }
        },
        serverSync = {
            remoteProvider.getGenerationInfo(generation).map {
                pokedexMapper.mapGenerationInfoToEntryEntityArray(it)
            }.onEach {
                localProvider.insertPokedexEntries(*it).firstOrNull()
            }.collect()
        }
    )

    private fun localPokedexEntryDataFlow(generation: Int) =
        localProvider.getPokedexByGeneration(generation).map {
            pokedexMapper.mapEntryEntityListToEntryModelList(it)
        }

    override fun getPokemonByNumber(number: Int, cacheMode: CacheMode): Flow<PokemonModel> =
        cachedDataFlow(
            cacheMode = cacheMode,
            isCached = {
                localPokemonDataFlow(number).firstOrNull() != null
            },
            getAllCached = {
                localPokemonDataFlow(number)
                    .distinctUntilChanged()
                    .collect { pokemon ->
                        pokemon?.let { emit(it) }
                    }
            },
            serverSync = {
                remoteProvider.getPokemonInfo(number).map {
                    pokemonMapper.mapPokemonInfoToPokemonEntity(it)
                }.onEach {
                    localProvider.insertPokemon(it).firstOrNull()
                }.collect()
            }
        )

    private fun localPokemonDataFlow(number: Int) = localProvider.getPokemonByNumber(number).map {
        pokemonMapper.mapPokemonEntityToPokemonModel(it)
    }

    override fun searchPokemon(query: String): Flow<List<PokedexEntryModel>> =
        localProvider.searchPokedex(query).map {
            pokedexMapper.mapEntryEntityListToEntryModelList(it)
        }
}