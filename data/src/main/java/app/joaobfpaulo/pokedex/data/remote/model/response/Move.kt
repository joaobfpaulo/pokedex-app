package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move") val move: Result,
    @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
)