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