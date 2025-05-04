package app.joaobfpaulo.pokedex.data.remote.model.response.mapper

import app.joaobfpaulo.pokedex.data.remote.model.response.PokemonList
import app.joaobfpaulo.pokedex.domain.model.PokedexEntryModel
import app.joaobfpaulo.pokedex.domain.model.PokedexModel
import javax.inject.Inject

class PokedexMapper @Inject constructor() {
    companion object {
        private const val SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        private const val SPRITE_URL_SUFFIX = ".png"
        private const val URL_SUFFIX = "/"
    }

    fun mapPokemonListToPokedexModel(response: PokemonList) : PokedexModel =
        PokedexModel(
            count = response.count,
            next = response.next,
            previous = response.previous,
            entries = response.results.map { entry ->
                val number = parseNumber(entry.url)
                PokedexEntryModel(
                    pokemonName = capitalizedName(entry.name),
                    imageUrl = buildSpriteUrl(number),
                    number = number
                )
            }
        )

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