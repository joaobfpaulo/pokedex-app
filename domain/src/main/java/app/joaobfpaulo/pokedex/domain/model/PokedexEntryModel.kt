package app.joaobfpaulo.pokedex.domain.model

import java.io.Serializable

data class PokedexEntryModel(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int
) : Serializable
