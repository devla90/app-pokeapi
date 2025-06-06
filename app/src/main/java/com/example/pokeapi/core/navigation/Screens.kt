/**
 * Este archivo contiene las diferentes pantallas de la aplicación, cada una representada por una clase
 * que define su comportamiento y apariencia. Aquí se encuentran las implementaciones de las
 * interfaces de usuario que serán mostradas al usuario durante la ejecución del programa.
 */

package com.example.pokeapi.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val url: String)
