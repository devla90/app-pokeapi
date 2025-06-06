/**
 * Este archivo contiene la implementación de un repositorio que se encarga de manejar la obtención y almacenamiento de datos relacionados con Pokémon.
 * Incluye funciones para obtener la lista de Pokémon, buscar un Pokémon por su nombre o ID, y guardar un Pokémon en la base de datos.
 * Además, proporciona métodos para actualizar la información de un Pokémon y eliminar un Pokémon de la base de datos.
 */

package com.example.pokeapi.domain.repository

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<Map<String, Pokemon>>
}