package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: Result
)