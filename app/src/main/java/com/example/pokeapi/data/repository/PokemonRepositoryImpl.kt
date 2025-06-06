/**
 * Este archivo contiene la implementación concreta de un repositorio de Pokémon.
 * Se encarga de manejar la lógica de obtención y almacenamiento de datos relacionados con Pokémon.
 * Incluye métodos para obtener la lista de Pokémon, buscar un Pokémon por su ID, y agregar un nuevo Pokémon.
 * Además, se encarga de la comunicación con la capa de datos para obtener la información necesaria.
 */

package com.example.pokeapi.data.repository

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.data.network.PokeApiService
import com.example.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Esta clase se encarga de implementar la lógica para obtener la lista de Pokémon
 * utilizando un servicio de API específico.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {
    override fun getPokemonList(limit: Int, offset: Int): Flow<Map<String, Pokemon>> = flow {
        val response = apiService.getPokemonList(limit, offset)
        val pokemonMap = response.results.associateBy { it.name }
        emit(pokemonMap)
    }
}