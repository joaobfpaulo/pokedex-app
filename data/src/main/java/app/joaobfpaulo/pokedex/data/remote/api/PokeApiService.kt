package app.joaobfpaulo.pokedex.data.remote.api

import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    companion object {
        private const val POKEMON_PATH = "pokemon"
        private const val POKEMON_NAME_PATH = "name"
        private const val POKEMON_INFO_PATH = "$POKEMON_PATH/{$POKEMON_NAME_PATH}"

        private const val LIMIT_QUERY_PARAM = "limit"
        private const val OFFSET_QUERY_PARAM = "offset"

    }

    @GET(POKEMON_PATH)
    suspend fun getPokemonList(
        @Query(LIMIT_QUERY_PARAM) limit: Int,
        @Query(OFFSET_QUERY_PARAM) offset: Int
    ): PokemonList

    @GET(POKEMON_INFO_PATH)
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): PokemonInfo

}