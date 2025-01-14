package com.example.pokeapi.data.repository

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.network.PokeApiService
import com.example.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {
    override fun getPokemonList(limit: Int, offset: Int): Flow<Map<String, Pokemon>> = flow {
        val response = apiService.getPokemonList(limit, offset)
        val pokemonMap = response.results.associateBy { it.name }
        emit(pokemonMap)
    }
}