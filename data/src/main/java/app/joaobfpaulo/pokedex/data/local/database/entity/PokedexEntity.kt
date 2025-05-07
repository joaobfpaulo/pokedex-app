package app.joaobfpaulo.pokedex.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokedex")
data class PokedexEntity(
    @PrimaryKey @ColumnInfo(name = "generation") val generation: Int,
    @ColumnInfo(name = "name") val name: String
)