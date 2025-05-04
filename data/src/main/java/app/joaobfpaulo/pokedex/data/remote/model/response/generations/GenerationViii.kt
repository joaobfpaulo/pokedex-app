package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.sprites.IconsX
import com.google.gson.annotations.SerializedName

data class GenerationViii(
    @SerializedName("icons") val icons: IconsX
)