package com.example.pokeapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase

class PokemonViewModelFactory(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(getPokemonListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
