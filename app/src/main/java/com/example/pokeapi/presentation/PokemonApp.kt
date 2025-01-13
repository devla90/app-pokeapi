package com.example.pokeapi.presentation

import android.app.Application
import com.example.pokeapi.data.network.PokeApiService
import com.example.pokeapi.data.repository.PokemonRepositoryImpl
import com.example.pokeapi.domain.repository.PokemonRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApp : Application() {
    lateinit var pokeApiService: PokeApiService
    lateinit var pokemonRepository: PokemonRepository
    override fun onCreate() {
        super.onCreate()
        pokeApiService = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            .create(PokeApiService::class.java)
        pokemonRepository = PokemonRepositoryImpl(pokeApiService)
    }
}