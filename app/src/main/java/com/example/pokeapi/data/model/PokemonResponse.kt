/**
 * Este archivo contiene la definición de la clase PokemonResponse, la cual representa la respuesta
 * obtenida al realizar una solicitud de información sobre un Pokémon a través de una API.
 * La clase incluye los atributos necesarios para almacenar los datos del Pokémon, como su nombre,
 * número en la Pokédex, tipos, habilidades y estadísticas.
 */

package com.example.pokeapi.data.model

data class PokemonResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)