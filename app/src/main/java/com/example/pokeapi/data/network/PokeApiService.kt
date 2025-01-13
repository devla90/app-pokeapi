package com.example.pokeapi.data.network

import com.example.pokeapi.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonResponse
}