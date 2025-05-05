package app.joaobfpaulo.pokedex.data.remote.model.response.mapper

import app.joaobfpaulo.pokedex.data.remote.model.response.ResultList
import javax.inject.Inject

class GenerationMapper @Inject constructor() {
    companion object {
        private const val URL_SUFFIX = "/"
    }

    fun mapGenerationListToGenerationListModel(response: ResultList) : Map<Int, String> =
        response.results.associate {
            parseNumber(it.url) to parseName(it.name)
        }

    private fun parseNumber(url: String) : Int {
        return if(url.endsWith(URL_SUFFIX)) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
           url.takeLastWhile { it.isDigit() }
        }.toInt()
    }

    private fun parseName(name: String) : String {
        val names = name.split('-')
        val first = names.first().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val last = names.last().uppercase()

        return "$first $last"
    }
}