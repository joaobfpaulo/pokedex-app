package app.joaobfpaulo.pokedex.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
    @SerializedName("level_learned_at") val levelLearnedAt: Int,
    @SerializedName("move_learn_method") val moveLearnMethod: Result,
    @SerializedName("version_group") val versionGroup: Result
)