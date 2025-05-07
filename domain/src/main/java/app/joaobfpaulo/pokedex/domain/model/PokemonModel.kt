package app.joaobfpaulo.pokedex.domain.model


import app.joaobfpaulo.pokedex.domain.model.enums.Stat
import app.joaobfpaulo.pokedex.domain.model.enums.Type
import java.io.Serializable

data class PokemonModel(
    val number: Int,
    val height: Int,
    val name: String,
    val sprite: String,
    val stats: Map<Stat, Int>,
    val types: List<Type>,
    val weight: Int
) : Serializable