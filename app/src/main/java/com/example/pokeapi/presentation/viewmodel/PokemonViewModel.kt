package com.example.pokeapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    fun getPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            try {
                getPokemonListUseCase(limit, offset)
                    .flowOn(Dispatchers.IO)
                    .collect { response ->
                        _pokemonList.value = response.results
                        Log.i("pokeapi", pokemonList.value.toString())
                    }    
            }catch (e: Exception){
                Log.e("pokeapi",e.toString())
            }
            
        }
    }

    fun filterText(text: String) {
        Log.i("pokeapi","text change: $text")
    }
}