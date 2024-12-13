package com.example.flowtogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat

// Importación de íconos predeterminados
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilitar modo Edge-to-Edge
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true  // Ajustar el color de la barra de estado
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // Ocultar las barras del sistema

        setContent {
            Material3App()  // Llamamos a la interfaz de Compose con Material 3
        }

        // Ajuste de padding con los insets del sistema (barra de navegación y barra de estado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3App() {
    var text by remember { mutableStateOf("") }  // Usamos String en lugar de TextFieldValue
    var showSnackbar by remember { mutableStateOf(false) }
    var isSwitchChecked by remember { mutableStateOf(false) }

    // Aplicar el tema de Material 3
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Material 3 Demo") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            floatingActionButton = {
                // Floating Action Button
                FloatingActionButton(
                    onClick = { showSnackbar = !showSnackbar },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    // Usamos el ícono Add desde la colección Icons predeterminada
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),  // Asegura que haya espacio alrededor
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // TextField de Material 3
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Enter something") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Switch de Material 3
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Enable Feature")
                        Switch(
                            checked = isSwitchChecked,
                            onCheckedChange = { isSwitchChecked = it },
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Card de Material 3
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("This is a Card")
                            Text("Material Design 3 styles.")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider para separar visualmente
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Snackbar cuando se presiona el FAB
                    if (showSnackbar) {
                        Snackbar(
                            modifier = Modifier.padding(bottom = 16.dp),
                            content = { Text("FAB was clicked!") },
                            action = {
                                Button(onClick = { showSnackbar = false }) {
                                    Text("Dismiss")
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Material3App()
}
