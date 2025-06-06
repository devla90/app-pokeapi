/**
 * Este archivo contiene la interfaz PokeApiService, la cual define los endpoints para realizar
 * peticiones a la API de Pokémon. Incluye métodos para obtener información de Pokémon específicos,
 * así como para buscar Pokémon por nombre o número de Pokédex. Además, proporciona métodos para
 * obtener la lista de tipos de Pokémon, así como la lista de habilidades disponibles.
 */

package com.example.pokeapi.data.network

import com.example.pokeapi.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonResponse
}