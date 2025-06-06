package com.example.pokeapi.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.presentation.PokemonApp
import com.example.pokeapi.presentation.viewmodel.PokemonViewModel
import com.example.pokeapi.presentation.viewmodel.PokemonViewModelFactory
import com.example.pokeapi.presentation.viewmodel.UiState

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
    onPokemonClick: (String) -> Unit
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
        TitleSection()
        SearchSection(
            text = text,
            onTextChanged = { newText ->
                text = newText
            },
            onSearchClick = {
                viewModel.filterPokemonByName(text)
            }
        )

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text(
                    "Error: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is UiState.Success -> {
                val pokemonMap = (uiState as UiState.Success).data
                ListSection(
                    pokemonMap = pokemonMap,
                    onPokemonClick
                )
            }

            UiState.Idle -> {}
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "App Pokemon",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleSection() {
    TitleSection()
}

@Composable
fun SearchSection(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(color = Color.Green)
            .shadow(4.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                label = {
                    Text("Pokemon")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(onClick = onSearchClick) {
                Text("Search")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchSection() {
    SearchSection(
        text = "Bulbasaur",
        onTextChanged = {},
        onSearchClick = {}
    )
}

@Composable
fun ListSection(
    pokemonMap: Map<String, Pokemon>,
    onPokemonClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        //contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(pokemonMap.values.toList()) { pokemon ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(4.dp)
                    //.border(2.dp, color = Color.Black, shape = RoundedCornerShape(24,24,24,24))
                    .clickable {
                        onPokemonClick(pokemon.url)
                    },
                border = BorderStroke(width = 1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Text(
                    pokemon.name,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListSection() {
    val pokemonMap = mapOf(
        "bulbasaur" to Pokemon("bulbasaur", ""),
        "charmander" to Pokemon("charmander", ""),
        "squirtle" to Pokemon(name = "Squirtle", url = "")
    )
    ListSection(
        pokemonMap = pokemonMap,
        onPokemonClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGradientBackground() {
    GradientBackground()
}
@Composable
fun GradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Green)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
//                .drawIntoCanvas { canvas ->
//                    val path = Path().apply {
//                        moveTo(0f, 300.dp.toPx())
//                        quadTo(
//                            size.width / 2, 250.dp.toPx(),
//                            size.width, 300.dp.toPx()
//                        )
//                        lineTo(size.width, 0f)
//                        lineTo(0f, 0f)
//                        close()
//                    }
//                    canvas.drawPath(path, paint)
//                }
        )
    }
}