package app.joaobfpaulo.pokedex.data.remote.api

import app.joaobfpaulo.pokedex.data.remote.model.response.GenerationInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.ResultList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    companion object {
        private const val GENERATION_PATH = "generation"
        private const val NUMBER_PATH = "number"

        private const val GENERATION_INFO_PATH = "$GENERATION_PATH/{$NUMBER_PATH}"

        private const val POKEMON_PATH = "pokemon"

        private const val POKEMON_INFO_PATH = "$POKEMON_PATH/{$NUMBER_PATH}"
    }

    @GET(GENERATION_PATH)
    suspend fun getGenerationList(): ResultList

    @GET(GENERATION_INFO_PATH)
    suspend fun getGenerationInfo(
        @Path(NUMBER_PATH) number: Int
    ): GenerationInfo

    @GET(POKEMON_INFO_PATH)
    suspend fun getPokemonInfo(
        @Path(NUMBER_PATH) number: Int
    ): PokemonInfo

}