package app.joaobfpaulo.pokedex.data.remote.model.response.mapper

import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import app.joaobfpaulo.pokedex.domain.model.enums.Stat
import app.joaobfpaulo.pokedex.domain.model.enums.Type
import javax.inject.Inject

class PokemonMapper @Inject constructor() {
    fun mapPokemonInfoToPokemonModel(response: PokemonInfo) : PokemonModel =
        PokemonModel(
            id = response.id,
            height = response.height,
            name = response.name,
            sprite = response.sprites.frontDefault,
            stats = response.stats.associate { stat -> parseStat(stat.stat.name) to stat.baseStat },
            types = response.types.map { type -> parseType(type.type.name) },
            weight = response.weight
        )

    private fun parseStat(statName: String): Stat {
        return Stat.entries.find { it.id == statName.lowercase() } ?: Stat.UNKNOWN
    }

    private fun parseType(typeName: String) : Type {
        return Type.entries.find { it.name.lowercase() == typeName.lowercase() } ?: Type.UNKNOWN
    }
}