package app.joaobfpaulo.pokedex.presentation.navigation.screens

const val GENERATION_POKEDEX_PATH = "generation_pokedex"
const val DETAIL_PATH = "detail"
const val ERROR_PATH = "detail"
const val GENERATION_PATH = "generation_path"
const val NUMBER_PATH = "number"
const val NAME_PATH = "name"
const val DOMINANT_COLOR_PATH = "dominantColor"
private const val PATH_SEPARATOR = "/"
private const val PATH_PREFIX = "{"
private const val PATH_SUFFIX = "}"

enum class Route(val value: String, val paths: List<String>) {
    Error(
        value = ERROR_PATH,
        paths = emptyList()
    ),
    Detail(
        value = DETAIL_PATH,
        paths = listOf(
            DOMINANT_COLOR_PATH,
            NAME_PATH,
            NUMBER_PATH
        )
    ),
    Generation(
        value = GENERATION_POKEDEX_PATH,
        paths = listOf(GENERATION_PATH)
    );

    companion object {
        fun Route.getRoute() : String = "$value${paths.joinToString(
            separator = PATH_SEPARATOR, 
            prefix = PATH_SEPARATOR, 
            transform = { "$PATH_PREFIX$it$PATH_SUFFIX" }
        )}"
        fun Route.createRoute(vararg args: String) : String {
            var route = getRoute().replace(PATH_PREFIX, "").replace(PATH_SUFFIX, "")
            require(args.size == paths.size) {
                "The number of args(${args.size}) doesn't match with the number of paths(${paths.size})"
            }
            repeat(paths.size) { idx ->
                route = route.replace(paths[idx], args[idx])
            }
            return route
        }
    }
}