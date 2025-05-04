package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability") val ability: Result,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)