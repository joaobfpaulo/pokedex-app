package app.joaobfpaulo.pokedex.data.di

import android.content.Context
import androidx.room.Room
import app.joaobfpaulo.pokedex.data.BuildConfig
import app.joaobfpaulo.pokedex.data.local.database.PokedexDatabase
import app.joaobfpaulo.pokedex.data.local.database.dao.PokedexDao
import app.joaobfpaulo.pokedex.data.local.database.dao.PokemonDao
import app.joaobfpaulo.pokedex.data.local.database.typeconverter.StatsTypeConverter
import app.joaobfpaulo.pokedex.data.local.database.typeconverter.TypesTypeConverter
import app.joaobfpaulo.pokedex.data.remote.api.PokeApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun gson(): Gson = Gson()

    @Singleton
    @Provides
    fun providePokeApiService(retrofit: Retrofit) : PokeApiService =
        retrofit.create(PokeApiService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.POKE_API_BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        statsTypeConverter: StatsTypeConverter,
        typesTypeConverter: TypesTypeConverter
    ): PokedexDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PokedexDatabase::class.java,
            PokedexDatabase.DATABASE_NAME
        )
            .addTypeConverter(statsTypeConverter)
            .addTypeConverter(typesTypeConverter)
            .build()
    }

    @Singleton
    @Provides
    fun providePokedexDao(database: PokedexDatabase): PokedexDao =
        database.pokedexDao()


    @Singleton
    @Provides
    fun providePokemonDao(database: PokedexDatabase): PokemonDao =
        database.pokemonDao()
}