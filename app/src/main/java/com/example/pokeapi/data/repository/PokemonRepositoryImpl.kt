package com.example.pokeapi.data.repository

import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.network.PokeApiService
import com.example.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(private val apiService: PokeApiService): PokemonRepository {
    override fun getPokemonList(limit: Int, offset: Int): Flow<PokemonResponse> = flow {
        emit(apiService.getPokemonList(limit, offset))
    }
}