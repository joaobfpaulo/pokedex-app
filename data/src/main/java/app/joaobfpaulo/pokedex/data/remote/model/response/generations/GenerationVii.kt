package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.UltraSunUltraMoon
import app.joaobfpaulo.pokedex.data.remote.model.response.sprites.Icons
import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons") val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
)