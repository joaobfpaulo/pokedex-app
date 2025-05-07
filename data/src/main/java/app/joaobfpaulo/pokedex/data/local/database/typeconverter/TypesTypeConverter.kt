package app.joaobfpaulo.pokedex.data.local.database.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

@ProvidedTypeConverter
class TypesTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromTypesList(reference: List<String>): String {
        return gson.toJson(reference)
    }

    @TypeConverter
    fun toTypesList(value: String?): List<String> {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
}