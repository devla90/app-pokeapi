package com.example.pokeapi.domain.repository

import com.example.pokeapi.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<PokemonResponse>
}