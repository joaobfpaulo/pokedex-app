package app.joaobfpaulo.pokedex.data.remote.model.response.games


import com.google.gson.annotations.SerializedName

data class Emerald(
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_shiny") val frontShiny: String
)