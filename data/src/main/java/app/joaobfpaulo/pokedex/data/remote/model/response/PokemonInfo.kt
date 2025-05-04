package app.joaobfpaulo.pokedex.data.remote.model.response


import app.joaobfpaulo.pokedex.data.remote.model.response.sprites.Sprites
import com.google.gson.annotations.SerializedName

data class PokemonInfo(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("back_default") val forms: List<Result>,
    @SerializedName("game_indices") val gameIndices: List<GameIndex>,
    @SerializedName("height") val height: Int,
    @SerializedName("held_items") val heldItems: List<Any>,
    @SerializedName("id") val id: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("past_types") val pastTypes: List<Any>,
    @SerializedName("species") val species: Result,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("weight") val weight: Int
)