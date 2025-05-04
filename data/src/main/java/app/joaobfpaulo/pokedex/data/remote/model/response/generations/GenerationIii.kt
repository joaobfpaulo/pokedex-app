package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.Emerald
import app.joaobfpaulo.pokedex.data.remote.model.response.games.FireredLeafgreen
import app.joaobfpaulo.pokedex.data.remote.model.response.games.RubySapphire
import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @SerializedName("emerald") val emerald: Emerald,
    @SerializedName("firered-leafgreen") val fireredLeafGreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire
)