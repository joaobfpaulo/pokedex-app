package app.joaobfpaulo.pokedex.data.remote.model.response


import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationI
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationIi
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationIii
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationIv
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationV
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationVi
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationVii
import app.joaobfpaulo.pokedex.data.remote.model.response.generations.GenerationViii
import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i") val generationI: GenerationI,
    @SerializedName("generation-ii") val generationIi: GenerationIi,
    @SerializedName("generation-iii") val generationIii: GenerationIii,
    @SerializedName("generation-iv") val generationIv: GenerationIv,
    @SerializedName("generation-v") val generationV: GenerationV,
    @SerializedName("generation-vi") val generationVi: GenerationVi,
    @SerializedName("generation-vii") val generationVii: GenerationVii,
    @SerializedName("generation-viii") val generationViii: GenerationViii
)