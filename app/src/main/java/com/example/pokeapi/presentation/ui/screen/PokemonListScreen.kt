package com.example.pokeapi.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.presentation.PokemonApp
import com.example.pokeapi.presentation.viewmodel.PokemonViewModel
import com.example.pokeapi.presentation.viewmodel.PokemonViewModelFactory
import com.example.pokeapi.presentation.viewmodel.UiState

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = viewModel()
) {

//    val context = LocalContext.current
//    val application = context.applicationContext as PokemonApp
//    val pokemonRepository = application.pokemonRepository
//    val getPokemonListUseCase = GetPokemonListUseCase(pokemonRepository)
//    val viewModelFactory = PokemonViewModelFactory(getPokemonListUseCase)
//    val viewModel: PokemonViewModel = viewModel(factory = viewModelFactory)

    var text by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.filterText(text)
            },
            label = { Text("Pokemon") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            viewModel.filterPokemonByName(text)
        }) {
            Text("Buscar")
        }

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text("Error: $errorMessage", color = Color.Red)
            }

            is UiState.Success -> {
                val pokemonMap = (uiState as UiState.Success).data
                LazyColumn {
                    items(pokemonMap.values.toList()) { pokemon ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                            Text(
                                pokemon.name,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }

            UiState.Idle -> {}
        }
    }
}