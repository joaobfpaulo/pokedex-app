package app.joaobfpaulo.pokedex.domain.model.enums

enum class Stat(val id: String) {
    ATTACK("attack"),
    DEFENSE("defense"),
    HP("hp"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed"),
    UNKNOWN("")
}