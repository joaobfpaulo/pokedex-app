package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GameIndex(
    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("version") val version: Result
)