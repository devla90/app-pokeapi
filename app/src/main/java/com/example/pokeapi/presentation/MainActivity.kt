/**
 * Este archivo contiene la implementación de la actividad principal de la aplicación en Kotlin.
 * Aquí se definen las funciones y métodos necesarios para la interacción del usuario con la interfaz gráfica,
 * así como la lógica de negocio asociada a las diferentes pantallas y funcionalidades.
 * Además, se establecen las conexiones con otros componentes de la aplicación y se gestionan los eventos
 * que ocurren durante la ejecución del programa.
 */

package com.example.pokeapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokeapi.core.navigation.NavigationWrapper
import com.example.pokeapi.presentation.ui.theme.PokeapiTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Clase principal que representa la actividad principal de la aplicación.
 * Se encarga de inicializar la interfaz de usuario y configurar el tema de la aplicación.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeapiTheme {
                NavigationWrapper()
            }
        }
    }
}

/**
 * Composable que muestra un saludo personalizado.
 *
 * @param name Nombre a incluir en el mensaje.
 * @param modifier Modificador opcional para el estilo.
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokeapiTheme {
        Greeting("Android")
    }
}