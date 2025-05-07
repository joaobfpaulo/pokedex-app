package app.joaobfpaulo.pokedex.data.remote.model.response.mapper

import app.joaobfpaulo.pokedex.data.local.database.entity.PokemonDetailEntity
import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonInfo
import app.joaobfpaulo.pokedex.domain.model.PokemonModel
import app.joaobfpaulo.pokedex.domain.model.enums.Stat
import app.joaobfpaulo.pokedex.domain.model.enums.Type
import javax.inject.Inject

class PokemonMapper @Inject constructor() {
    fun mapPokemonInfoToPokemonEntity(response: PokemonInfo) : PokemonDetailEntity =
        PokemonDetailEntity(
            number = response.id,
            height = response.height,
            name = response.name,
            sprite = response.sprites.frontDefault,
            stats = response.stats.associate { stat -> stat.stat.name to stat.baseStat },
            types = response.types.map { type -> type.type.name },
            weight = response.weight,
            generation = 0
        )

    fun mapPokemonEntityToPokemonModel(entity: PokemonDetailEntity?) : PokemonModel? =
        entity?.let {
            PokemonModel(
                number = it.number,
                height = it.height,
                name = it.name,
                sprite = it.sprite,
                stats = it.stats.mapKeys { parseStat(it.key) },
                types = it.types.map { type -> parseType(type) },
                weight = it.weight
            )
        }


    private fun parseStat(statName: String): Stat {
        return Stat.entries.find { it.id == statName.lowercase() } ?: Stat.UNKNOWN
    }

    private fun parseType(typeName: String) : Type {
        return Type.entries.find { it.name.lowercase() == typeName.lowercase() } ?: Type.UNKNOWN
    }
}