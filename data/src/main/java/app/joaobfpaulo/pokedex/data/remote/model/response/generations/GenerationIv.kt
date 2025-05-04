package app.joaobfpaulo.pokedex.data.remote.model.response.generations


import app.joaobfpaulo.pokedex.data.remote.model.response.games.DiamondPearl
import app.joaobfpaulo.pokedex.data.remote.model.response.games.HeartGoldSoulSilver
import app.joaobfpaulo.pokedex.data.remote.model.response.games.Platinum
import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartGoldSoulSilver: HeartGoldSoulSilver,
    @SerializedName("platinum") val platinum: Platinum
)