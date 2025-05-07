package app.joaobfpaulo.pokedex.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonDetailEntity(
    @PrimaryKey @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "generation") val generation: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "sprite") val sprite: String,
    @ColumnInfo(name = "stats") val stats: Map<String, Int>,
    @ColumnInfo(name = "types") val types: List<String>,
)