package app.joaobfpaulo.pokedex.domain.model


data class PokedexModel(
   val count: Int,
   val next: String,
   val previous: String?,
   val entries: List<PokedexEntryModel>
)