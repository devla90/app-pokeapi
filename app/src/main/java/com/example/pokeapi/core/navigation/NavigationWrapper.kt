/**
 * Este archivo contiene la implementación de la clase NavigationWrapper, la cual se encarga de gestionar la navegación
 * entre diferentes pantallas de la aplicación. Proporciona métodos para agregar, quitar y navegar a destinos específicos,
 * así como para manejar la navegación hacia atrás. Además, permite configurar transiciones personalizadas y manejar
 * eventos de navegación. Es un componente fundamental para la estructura de navegación de la aplicación en Kotlin.
 */

package com.example.pokeapi.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokeapi.presentation.ui.screen.PokemonDetailScreen
import com.example.pokeapi.presentation.ui.screen.PokemonListScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonList) {
        composable<PokemonList> {
            PokemonListScreen(
                onPokemonClick = { url ->
                    navController.navigate(PokemonDetail(url = url))
                }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(detail.url)
        }
    }

}