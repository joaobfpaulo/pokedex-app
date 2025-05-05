package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GenerationInfo(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("id") val id: Int,
    @SerializedName("main_region") val mainRegion: Result,
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("name") val name: String,
    @SerializedName("names") val order: List<Any>,
    @SerializedName("pokemon_species") val pokemonSpecies: List<Result>,
    @SerializedName("types") val types: List<Result>,
    @SerializedName("version_groups") val versionGroups: List<Result>
)