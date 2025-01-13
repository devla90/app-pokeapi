package com.example.pokeapi.domain.usecase

import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val repository: PokemonRepository) {
    operator fun invoke(limit: Int, offset: Int): Flow<PokemonResponse> {
        return repository.getPokemonList(limit, offset)
    }
}