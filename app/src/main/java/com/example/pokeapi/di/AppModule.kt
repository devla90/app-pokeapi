/**
 * Este archivo contiene la definición de los módulos de Dagger utilizados para la inyección de dependencias en la aplicación.
 * Incluye la configuración de los módulos necesarios para proveer instancias de las clases requeridas en diferentes partes del código.
 * Además, se encarga de la configuración de los componentes necesarios para la inyección de dependencias en las actividades y fragmentos de la aplicación.
 */

package com.example.pokeapi.di

import com.example.pokeapi.data.network.PokeApiService
import com.example.pokeapi.data.repository.PokemonRepositoryImpl
import com.example.pokeapi.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService{
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(apiService: PokeApiService): PokemonRepository {
        return PokemonRepositoryImpl(apiService)
    }
}