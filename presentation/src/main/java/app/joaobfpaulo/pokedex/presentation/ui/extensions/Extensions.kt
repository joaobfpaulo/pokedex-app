package app.joaobfpaulo.pokedex.presentation.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import app.joaobfpaulo.pokedex.domain.model.enums.Stat
import app.joaobfpaulo.pokedex.domain.model.enums.Type
import app.joaobfpaulo.pokedex.presentation.ui.theme.*
import app.joaobfpaulo.pokedex.presentation.R

fun Type.parseTypeName() : String {
    return name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun Type.parseTypeToColor(): Color {
    return when(this) {
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

fun Stat.parseStatToColor(): Color {
    return when(this) {
        Stat.ATTACK -> AtkColor
        Stat.DEFENSE -> DefColor
        Stat.HP -> HPColor
        Stat.SPECIAL_ATTACK -> SpAtkColor
        Stat.SPECIAL_DEFENSE -> SpDefColor
        Stat.SPEED -> SpdColor
        else -> Color.White
    }
}

@Composable
fun Stat.parseStatToAbbr(): String {
    return stringResource(
        when(this) {
            Stat.ATTACK -> R.string.attack
            Stat.DEFENSE -> R.string.defence
            Stat.HP -> R.string.hit_points
            Stat.SPECIAL_ATTACK -> R.string.special_attack
            Stat.SPECIAL_DEFENSE -> R.string.special_defense
            else -> R.string.speed
        }
    )


}