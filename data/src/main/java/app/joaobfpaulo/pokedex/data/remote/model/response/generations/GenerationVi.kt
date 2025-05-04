package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.OmegaRubyAlphaSapphire
import app.joaobfpaulo.pokedex.data.remote.model.response.games.XY
import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegaRubyAlphaSapphire,
    @SerializedName("x-y") val xY: XY
)