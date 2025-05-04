package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.Crystal
import app.joaobfpaulo.pokedex.data.remote.model.response.games.Gold
import app.joaobfpaulo.pokedex.data.remote.model.response.games.Silver
import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal") val crystal: Crystal,
    @SerializedName("gold") val gold: Gold,
    @SerializedName("silver") val silver: Silver
)