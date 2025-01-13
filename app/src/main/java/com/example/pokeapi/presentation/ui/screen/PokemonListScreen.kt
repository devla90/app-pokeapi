package com.example.pokeapi.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.presentation.PokemonApp
import com.example.pokeapi.presentation.viewmodel.PokemonViewModel
import com.example.pokeapi.presentation.viewmodel.PokemonViewModelFactory

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val application = context.applicationContext as PokemonApp
    val pokemonRepository = application.pokemonRepository
    val getPokemonListUseCase = GetPokemonListUseCase(pokemonRepository)
    val viewModelFactory = PokemonViewModelFactory(getPokemonListUseCase)
    val viewModel: PokemonViewModel = viewModel(factory = viewModelFactory)

    val pokemonList by viewModel.pokemonList.collectAsState()
    //val filteredItems by viewModel.filteredItems.collectAsStateWithLifecycle()
    var text by rememberSaveable { mutableStateOf("") }

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
            viewModel.getPokemonList(20, 0)
        }) {
            Text("Buscar")
        }
        LazyColumn {
            items(pokemonList) { pokemon ->
                Text(pokemon.name)
            }
        }
    }
}