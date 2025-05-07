package app.joaobfpaulo.pokedex.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokedexEntry")
data class PokedexEntryEntity(
    @PrimaryKey @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "generation") val generation: Int,
    @ColumnInfo(name = "displayName") val displayName: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sprite") val sprite: String
)