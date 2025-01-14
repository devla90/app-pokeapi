package com.example.pokeapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val data: Map<String, Pokemon>) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    private var fullPokemon: Map<String, Pokemon> = emptyMap()

    init {
        getPokemonList(20, 0)
    }

    fun getPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                getPokemonListUseCase(limit, offset)
                    .flowOn(Dispatchers.IO)
                    .collect { response ->
                        fullPokemon = response
                        _uiState.value = UiState.Success(response)
                        Log.i("pokeapi", response.toString())
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "unknow error")
                Log.e("pokeapi", e.toString())
            }
        }
    }

    fun filterText(text: String) {
        Log.i("pokeapi", "text change: $text")
    }

    fun filterPokemonByName(name: String){
        val filteredMap = fullPokemon.filterKeys { it.contains(name, ignoreCase = true) }
        _uiState.value = UiState.Success(filteredMap)
    }
}