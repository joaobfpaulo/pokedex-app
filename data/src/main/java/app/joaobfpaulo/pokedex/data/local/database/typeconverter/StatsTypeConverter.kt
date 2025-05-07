package app.joaobfpaulo.pokedex.data.local.database.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

@ProvidedTypeConverter
class StatsTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromStatMap(reference: Map<String, Int>): String {
        return gson.toJson(reference)
    }

    @TypeConverter
    fun toStatMap(value: String?): Map<String, Int> {
        val mapType: Type = object : TypeToken<Map<String, Int>>() {}.type
        return gson.fromJson(value, mapType)
    }
}