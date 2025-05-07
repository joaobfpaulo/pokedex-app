package app.joaobfpaulo.pokedex.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntity
import app.joaobfpaulo.pokedex.data.local.database.entity.PokedexEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokedex(vararg entities: PokedexEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(vararg entities: PokedexEntryEntity)

    @Query("DELETE FROM Pokedex")
    suspend fun deletePokedex()

    @Query("DELETE FROM PokedexEntry")
    suspend fun deletePokedexEntry()

    @Transaction
    suspend fun deleteAll() {
        deletePokedex()
        deletePokedexEntry()
    }

    @Query("SELECT * FROM Pokedex")
    fun getPokedex(): Flow<List<PokedexEntity>>

    @Query("SELECT * FROM PokedexEntry WHERE generation = :generation")
    fun getPokedexByGeneration(generation: Int): Flow<List<PokedexEntryEntity>>

    @Query("SELECT * FROM PokedexEntry WHERE displayName LIKE :query OR name LIKE :query")
    fun searchPokedex(query: String): Flow<List<PokedexEntryEntity>>
}