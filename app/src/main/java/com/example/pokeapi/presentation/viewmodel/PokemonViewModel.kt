package com.example.pokeapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    fun getPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            getPokemonListUseCase(limit, offset).collect { response ->
                _pokemonList.value = response.results
            }
        }
    }
}