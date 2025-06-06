/**
 * Este archivo contiene la implementación de la clase PokemonViewModelFactory, la cual se encarga de crear instancias
 * del ViewModel de Pokemon. Esta clase es utilizada para proveer una instancia del ViewModel con los parámetros
 * necesarios para su correcto funcionamiento, como el repositorio de Pokemon. Además, se encarga de manejar la
 * creación de instancias de ViewModel de forma segura y eficiente, siguiendo las mejores prácticas de arquitectura
 * de Android.
 */

package com.example.pokeapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase

/**
 * Clase encargada de crear instancias de PokemonViewModel para la arquitectura MVVM.
 */
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
