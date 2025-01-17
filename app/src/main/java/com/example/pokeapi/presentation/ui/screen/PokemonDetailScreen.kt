package com.example.pokeapi.presentation.ui.screen

import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapi.R

@Composable
fun PokemonDetailScreen(
    url: String,
//    pokemon: Pokemon,
//    onBackClicked: () -> Unit,
//    onHeartClicked: () -> Unit
) {
    AppBarDetail()
}

@Composable
fun PreviewAppBarDetail() {
    AppBarDetail()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarDetail(
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF78BEA6),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,

            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                HeaderDetailPokemon()
                CardDetailPokemon()
            }
        }
    )
}

@Preview
@Composable
fun PreviewHeaderDetailPokemon() {
    HeaderDetailPokemon()
}

@Composable
fun HeaderDetailPokemon() {
    Column(modifier = Modifier
        .background(Color(0xFF78BEA6))
        .padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Bulbasaur",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text = "001",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        Row {
            Card(
                modifier = Modifier
                    .padding(0.dp, 8.dp, 8.dp, 8.dp),
                colors = CardDefaults.cardColors(Color(0xFF4DA98C))
            ) {
                Text(
                    text = "Grass",
                    modifier = Modifier.padding(4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Card(
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 8.dp),
                colors = CardDefaults.cardColors(Color(0xFF4DA98C))
            ) {
                Text(
                    text = "Poison",
                    modifier = Modifier.padding(4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardDetailPokemon() {
    CardDetailPokemon()
}

@Composable
fun CardDetailPokemon() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF78BEA6))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 105.dp)
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            ContentDetailPokemon()
        }
        Image(
            painter = painterResource(
                id = R.drawable.bulbasaur
            ),
            contentDescription = "Imagen detalle",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
            //.offset(y = (-10).dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentDetailPokemon() {
    ContentDetailPokemon()
}

@Composable
fun ContentDetailPokemon() {
    Column(modifier = Modifier.padding(8.dp, 16.dp)) {
        Text(
            "About", modifier = Modifier
                .padding(top = 12.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(thickness = 2.dp, color = Color.Black)
        RowDetailInfo("Species", "Seed")
        RowDetailInfo("Height", "2'3.6 (0.70cm)")
        RowDetailInfo("Weight", "15.2 libs (6.9 kg)")
        RowDetailInfo("Abilities", "Overgrow, Chlorophyl")
        Text(
            "Breeding", modifier = Modifier
                .padding(top = 12.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(thickness = 2.dp, color = Color.Black)
        RowDetailInfo("Egg Groups", "Monster")
        RowDetailInfo("Egg Cycle", "Grass")
    }
}

@Composable
fun RowDetailInfo(description: String, contentDesc: String) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = description,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Text(
            text = contentDesc,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
    }
}

