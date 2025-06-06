/**
 * Este archivo contiene la lógica de negocio para obtener una lista de Pokémon.
 * Utiliza el patrón de diseño UseCase para encapsular la lógica de obtener la lista de Pokémon
 * de una fuente de datos externa.
 * 
 * El caso de uso GetPokemonListUseCase se encarga de manejar la obtención de la lista de Pokémon
 * y proporciona métodos para interactuar con esta funcionalidad.
 */

package com.example.pokeapi.domain.usecase

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonResponse
import com.example.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Clase que representa un caso de uso para obtener una lista de Pokémon.
 */class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(limit: Int, offset: Int): Flow<Map<String, Pokemon>> {
        return repository.getPokemonList(limit, offset)
    }
}