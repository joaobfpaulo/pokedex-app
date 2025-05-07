package app.joaobfpaulo.pokedex.data.remote.model.response.mapper

import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntryEntity
import app.joaobfpaulo.pokedex.data.remote.model.response.GenerationInfo
import app.joaobfpaulo.pokedex.data.remote.model.response.ResultList
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import javax.inject.Inject

class PokedexMapper @Inject constructor() {
    companion object {
        private const val SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        private const val SPRITE_URL_SUFFIX = ".png"
        private const val URL_SUFFIX = "/"
    }

    fun mapPokemonEntityListToPokedexModel(entities: List<PokedexEntity>) : PokedexModel =
        PokedexModel(
            generations = entities.associate { entity ->
                entity.generation to entity.name
            }
        )



    fun mapGenerationListToPokedexEntityArray(response: ResultList) : Array<PokedexEntity> =
        response.results.map {
            PokedexEntity(
                generation = parseNumber(it.url),
                name = parseName(it.name)
            )
        }.toTypedArray()

    fun mapGenerationInfoToEntryEntityArray(generationInfo: GenerationInfo) : Array<PokedexEntryEntity> =
        generationInfo.pokemonSpecies.sortedBy { parseNumber(it.url) }.map { entry ->
            val number = parseNumber(entry.url)
            PokedexEntryEntity(
                displayName = "#$number ${capitalizedName(entry.name)}",
                name = entry.name,
                sprite = buildSpriteUrl(number),
                number = number,
                generation = generationInfo.id
            )
        }.toTypedArray()

    fun mapEntryEntityListToEntryModelList(entities: List<PokedexEntryEntity>) : List<PokedexEntryModel> =
        entities.map(::mapEntryEntityToEntryModel)

    private fun mapEntryEntityToEntryModel(entryEntity: PokedexEntryEntity) : PokedexEntryModel =
        PokedexEntryModel(
            pokemonName = entryEntity.displayName,
            imageUrl = entryEntity.sprite,
            number = entryEntity.number
        )

    private fun parseName(name: String) : String {
        val names = name.split('-')
        val first = names.first().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val last = names.last().uppercase()

        return "$first $last"
    }

    private fun parseNumber(url: String) : Int {
        return if(url.endsWith(URL_SUFFIX)) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
           url.takeLastWhile { it.isDigit() }
        }.toInt()
    }

    private fun capitalizedName(name: String) : String {
        return name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }

    private fun buildSpriteUrl(number: Int) : String {
        return "$SPRITE_URL$number$SPRITE_URL_SUFFIX"
    }
}