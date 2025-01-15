package com.example.pokeapi.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailScreen(
    url: String,
    //pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
//    val detailsState by pokemonDetailsViewModel.detailsState.collectAsState()
//
//    LaunchedEffect(url) {
//        pokemonDetailsViewModel.fetchPokemonDetails(url)
//    }
//
//    when (detailsState) {
//        is DetailsUiState.Loading -> {
//            CircularProgressIndicator()
//        }
//        is DetailsUiState.Error -> {
//            val errorMessage = (detailsState as DetailsUiState.Error).message
//            Text("Error: $errorMessage", color = Color.Red)
//        }
//        is DetailsUiState.Success -> {
//            val details = (detailsState as DetailsUiState.Success).data
//            Column {
//                Text("Nombre: ${details.name}")
//                Text("Experiencia base: ${details.base_experience}")
//            }
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        Text("Vista DETALLE -> $url", color = Color.Blue)
    }
}
