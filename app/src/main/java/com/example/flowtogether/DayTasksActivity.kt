package com.example.flowtogether

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flowtogether.ui.CustomButtons
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

class DayTasksActivity : AppCompatActivity() {

    private val tasks = mutableStateListOf<String>()  // Usamos mutableStateListOf para Compose

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedDate = intent.getStringExtra("selected_date") ?: "Fecha no especificada"

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Tareas del día: $selectedDate",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    TaskList(tasks = tasks)  // Muestra la lista de tareas

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomButtons(
                        onAddTaskClick = {
                            val newTask = "Tarea para $selectedDate - ${tasks.size + 1}"
                            tasks.add(newTask)
                            Log.d("DayTasksActivity", "Tarea añadida: $newTask")
                        },
                        onBackClick = {
                            finish()
                            Log.d("DayTasksActivity", "Volviendo al calendario")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskCard(task: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = task, style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = { /* Implementar opciones en el futuro */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn {
        items(tasks) { task ->
            TaskCard(task = task)
        }
    }
}
