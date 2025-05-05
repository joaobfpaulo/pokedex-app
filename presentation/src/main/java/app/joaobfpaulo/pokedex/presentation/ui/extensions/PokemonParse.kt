package app.joaobfpaulo.pokedex.presentation.ui.extensions

import androidx.compose.ui.graphics.Color
import app.joaobfpaulo.pokedex.domain.model.enums.Stat
import app.joaobfpaulo.pokedex.domain.model.enums.Type
import app.joaobfpaulo.pokedex.presentation.ui.theme.*

fun parseTypeName(type: Type) : String {
    return type.name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun parseTypeToColor(type: Type): Color {
    return when(type) {
        Type.BUG -> TypeBug
        Type.DARK -> TypeDark
        Type.DRAGON -> TypeDragon
        Type.ELECTRIC -> TypeElectric
        Type.FAIRY -> TypeFairy
        Type.FIRE -> TypeFire
        Type.FIGHTING -> TypeFighting
        Type.FLYING -> TypeFlying
        Type.GHOST -> TypeGhost
        Type.GRASS -> TypeGrass
        Type.GROUND -> TypeGround
        Type.NORMAL -> TypeNormal
        Type.ICE -> TypeIce
        Type.ROCK -> TypeRock
        Type.POISON -> TypePoison
        Type.PSYCHIC -> TypePsychic
        Type.STEEL -> TypeSteel
        Type.WATER -> TypeWater
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat) {
        Stat.ATTACK -> AtkColor
        Stat.DEFENSE -> DefColor
        Stat.HP -> HPColor
        Stat.SPECIAL_ATTACK -> SpAtkColor
        Stat.SPECIAL_DEFENSE -> SpDefColor
        Stat.SPEED -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat) {
        Stat.ATTACK -> "Atk"
        Stat.DEFENSE -> "Def"
        Stat.HP -> "HP"
        Stat.SPECIAL_ATTACK -> "SpAtk"
        Stat.SPECIAL_DEFENSE -> "SpDef"
        Stat.SPEED -> "Spd"
        else -> ""
    }
}