package app.joaobfpaulo.pokedex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.joaobfpaulo.pokedex.data.local.database.dao.PokedexDao
import app.joaobfpaulo.pokedex.data.local.database.dao.PokemonDao
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntryEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokemonDetailEntity
import app.joaobfpaulo.pokedex.data.local.database.typeconverter.StatsTypeConverter
import app.joaobfpaulo.pokedex.data.local.database.typeconverter.TypesTypeConverter

@Database(
    entities = [
        PokedexEntity::class,
        PokedexEntryEntity::class,
        PokemonDetailEntity::class
    ],
    version = 1
)
@TypeConverters(
    value = [
        StatsTypeConverter::class,
        TypesTypeConverter::class
    ]
)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "pokedex_database"
    }
}