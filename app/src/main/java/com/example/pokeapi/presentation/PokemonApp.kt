/**
 * Este archivo contiene la lógica principal de la aplicación PokemonApp, 
 * incluyendo la inicialización de la aplicación, la configuración de la interfaz de usuario
 * y la gestión de las funcionalidades relacionadas con los Pokémon.
 */

package com.example.pokeapi.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Esta clase representa la aplicación principal de PokemonApp.
 * Se encarga de inicializar y configurar la aplicación.
 */
@HiltAndroidApp
class PokemonApp : Application()