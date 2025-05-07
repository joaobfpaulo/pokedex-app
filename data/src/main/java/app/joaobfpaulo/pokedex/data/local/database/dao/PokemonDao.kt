package app.joaobfpaulo.pokedex.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.joaobfpaulo.pokedex.data.local.database.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PokemonDetailEntity)

    @Query("DELETE FROM Pokemon")
    suspend fun deleteAll()

    @Query("SELECT * FROM Pokemon WHERE number = :number")
    fun getPokemonByNumber(number: Int): Flow<PokemonDetailEntity>
}