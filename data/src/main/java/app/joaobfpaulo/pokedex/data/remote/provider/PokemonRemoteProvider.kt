package app.joaobfpaulo.pokedex.data.remote.provider

import app.joaobfpaulo.pokedex.data.remote.api.PokeApiService
import app.joaobfpaulo.pokedex.data.remote.model.response.GenerationInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.ResultList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteProvider @Inject constructor(
   private val api: PokeApiService
) {
    fun getGenerationList(): Flow<ResultList> = flow {
        emit(api.getGenerationList())
    }

    fun getGenerationInfo(generation: Int): Flow<GenerationInfo> = flow {
        emit(api.getGenerationInfo(generation))
    }

    fun getPokemonInfo(number: Int): Flow<PokemonInfo> = flow {
        emit(api.getPokemonInfo(number))
    }
}