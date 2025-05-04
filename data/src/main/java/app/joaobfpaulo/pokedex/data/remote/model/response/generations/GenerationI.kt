package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.Yellow
import app.joaobfpaulo.pokedex.data.remote.model.response.games.RedBlue
import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val redBlue: RedBlue,
    @SerializedName("yellow") val yellow: Yellow
)