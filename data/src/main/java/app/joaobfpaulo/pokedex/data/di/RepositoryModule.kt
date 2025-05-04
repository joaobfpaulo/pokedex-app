package app.joaobfpaulo.pokedex.data.di

import app.joaobfpaulo.pokedex.data.remote.repository.PokemonRepository
import app.joaobfpaulo.pokedex.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindIPokemonRepository(repository: PokemonRepository): IPokemonRepository
}