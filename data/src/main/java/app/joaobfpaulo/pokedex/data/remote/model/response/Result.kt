package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)